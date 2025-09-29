package com.co.technicaltest.auth_service.domain.port.input;

import com.co.technicaltest.auth_service.application.dto.LoginResponseDTO;
import com.co.technicaltest.auth_service.application.dto.LoginDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;

import java.util.Optional;

/**
 * Authotization use case.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public interface AuthUseCase {

    /**
     * Method for registering customer information.
     *
     * @param registerRequestDTO Data transfer object for register customer.
     * @return Optional<RegisterResponseDTO> Optional of data customer registered
     */
    Optional<RegisterResponseDTO> saveOnCustomerService(RegisterRequestDTO registerRequestDTO);

    /**
     * Authentication method for token generation.
     *
     * @param userLoginDto Data transfer object login credential.
     * @return Optional<LoginResponseDTO> Optional with login information, token, and status.
     */
    Optional<LoginResponseDTO> authenticate(LoginDTO userLoginDto);

    /**
     * Method for validating token signatures.
     *
     * @param token token.
     * @return Optional<String> Optional of validate token
     */
    Optional<String> validateToken(String token);
}
