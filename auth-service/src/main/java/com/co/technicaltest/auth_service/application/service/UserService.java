package com.co.technicaltest.auth_service.application.service;

import com.co.technicaltest.auth_service.application.enums.ExceptionMessage;
import com.co.technicaltest.auth_service.domain.port.output.ClientRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * User Detail service implementation.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final ClientRepositoryPort repository;

    public UserService(ClientRepositoryPort repository) {
        this.repository = repository;
    }

    /**
     * Method Translate your user entity (BD) to the Spring security object (UserDetails)..
     *
     * @param username username.
     * @return UserDetails Object form spring security
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.repository.findByUsernameOnCustomerService(username)
                .map(user -> new User(username, user.password(),
                        true,
                        true,
                        true,
                        true,
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))))
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(ExceptionMessage.USERNAME_NOT_FOUND.getMessage(), username)));
    }
}
