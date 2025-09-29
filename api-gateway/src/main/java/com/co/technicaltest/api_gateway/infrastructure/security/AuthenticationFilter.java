package com.co.technicaltest.api_gateway.infrastructure.security;


import com.co.technicaltest.api_gateway.infrastructure.shared.JwtUtil;
import com.co.technicaltest.api_gateway.infrastructure.shared.RouteValidator;
import com.co.technicaltest.api_gateway.infrastructure.exception.MissingAuthorizationHeaderException;
import com.co.technicaltest.api_gateway.infrastructure.exception.UnAuthorizedAccessToApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Authentication filter class validates whether a JWT was generated correctly and grants access to protected routes.
 *
 * @author Andres Polo on 2025/09/27.
 * @version 1.0.0
 */
@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    log.error("No se adjunto cabezera de autorizacion!");
                    throw new MissingAuthorizationHeaderException("Falta de cabezera de autorización en peticion!");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    log.error("Token Invalido!");
                    throw new UnAuthorizedAccessToApplicationException("Token invalido, no tiene acceso a la aplicacion!");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}