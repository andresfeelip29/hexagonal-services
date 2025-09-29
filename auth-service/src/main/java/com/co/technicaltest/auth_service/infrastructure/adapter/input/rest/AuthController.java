package com.co.technicaltest.auth_service.infrastructure.adapter.input.rest;

import com.co.technicaltest.auth_service.application.dto.LoginResponseDTO;
import com.co.technicaltest.auth_service.domain.port.input.AuthUseCase;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;
import com.co.technicaltest.auth_service.application.dto.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Security management controller.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthUseCase authService;


    public AuthController(AuthUseCase authService) {
        this.authService = authService;
    }


    /**
     * {@code POST /} : Customer register
     * <p>
     * Endpoint register customer on customer service
     *
     * @param registerRequestDTO register customer data.
     * @return {@link ResponseEntity<RegisterResponseDTO>} Object of response where the information and result of customer registered
     */
    @Operation(
            summary = "Register",
            description = "Endpoint to register customer on customer service",
            responses = {
                    @ApiResponse(responseCode = "200", description = "register successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the register customer!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation register object!")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return this.authService.saveOnCustomerService(registerRequestDTO)
                .map(result ->
                        ResponseEntity.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }


    /**
     * {@code POST /} : Login
     * <p>
     * Endpoint register customer on customer service.
     *
     * @param loginDTO objet contain username and password.
     * @return {@link ResponseEntity<LoginResponseDTO>} Object of response where the information and result for login, token and status.
     */
    @Operation(
            summary = "Login",
            description = "Endpoint to login on service",
            responses = {
                    @ApiResponse(responseCode = "200", description = "loggin successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the loggin!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation loggin object!")
            }
    )
    @PostMapping("/token")
    public ResponseEntity<LoginResponseDTO> authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        return this.authService.authenticate(loginDTO)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * {@code GET /} : Validate token
     * <p>
     * Endpoint to validate token.
     *
     * @param token token value.
     * @return {@link ResponseEntity<String>} Messages for token validate status.
     */
    @Operation(
            summary = "Validate",
            description = "Endpoint to validate token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "validate successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the validate token!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation token data!")
            }
    )
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        return this.authService.validateToken(token)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }
}
