package com.co.technicaltest.auth_service.application.service;


import com.co.technicaltest.auth_service.application.enums.ExceptionMessage;
import com.co.technicaltest.auth_service.domain.model.Customer;
import com.co.technicaltest.auth_service.domain.port.input.AuthUseCase;
import com.co.technicaltest.auth_service.domain.port.output.ClientRepositoryPort;
import com.co.technicaltest.auth_service.application.dto.LoginResponseDTO;
import com.co.technicaltest.auth_service.application.dto.LoginDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

/**
 * Use case port implemetation for Auth.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class AuthService implements AuthUseCase {

    private final ClientRepositoryPort repository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthService(ClientRepositoryPort repository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<RegisterResponseDTO> saveOnCustomerService(RegisterRequestDTO userRequestDto) {

        if (Objects.isNull(userRequestDto))
            return Optional.empty();

        log.info("Metodo: {}, para creacion en customer service de usuario: {}",
                "[saveOnCustomerService]", userRequestDto.name());

        return this.repository.saveOnCustomerService(Customer.builder()
                .name(userRequestDto.name())
                .username(userRequestDto.username())
                .identification(userRequestDto.identification())
                .age(userRequestDto.age())
                .gender(userRequestDto.gender())
                .address(userRequestDto.address())
                .status(userRequestDto.status())
                .password(passwordEncoder.encode(userRequestDto.password()))
                .phone(userRequestDto.phone())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<LoginResponseDTO> authenticate(LoginDTO loginDTO) {

        if (Objects.isNull(loginDTO))
            return Optional.empty();

        log.info("Metodo: {}, autenticacion y generacion de token para usuario: {}",
                "[authenticate]", loginDTO.username());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.username(),
                        loginDTO.password()));

        if (authenticate.isAuthenticated()) {
            return Optional.ofNullable(LoginResponseDTO.builder()
                    .token(this.generateToken(loginDTO.username()))
                    .result("Ok")
                    .build());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> validateToken(String token) {
        log.info("Metodo: {}, validando token {}",
                "[validateToken]", token);
        this.signToken(token);
        return ExceptionMessage.TOKEN_VALID.getMessage().describeConstable();
    }


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    /**
     * Method for verifying the token signature.
     *
     * @param token token.
     */
    private void signToken(final String token) {
        Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
    }


    /**
     * method for loading claims.
     *
     * @param userName username.
     * @return String token
     */
    private String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }


    /**
     * Method for generate token.
     *
     * @param claims   claims generated.
     * @param userName customer username.
     * @return String token
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }


    /**
     * Method for get key.
     *
     * @return Key key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
