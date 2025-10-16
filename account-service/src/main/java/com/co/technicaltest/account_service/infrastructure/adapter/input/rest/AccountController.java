package com.co.technicaltest.account_service.infrastructure.adapter.input.rest;

import com.co.technicaltest.account_service.application.dto.AccountRequestDTO;
import com.co.technicaltest.account_service.application.dto.AccountResponseDTO;
import com.co.technicaltest.account_service.domain.port.input.AccountUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Account management controller.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/cuentas")
@Tag(name = "Cuentas" , description = "API para cuentas")
public class AccountController {

    private final AccountUseCase accountUseCase;

    public AccountController(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }


    /**
     * {@code GET /} : Get all accounts
     * <p>
     * Endpoint get all customer.
     *
     * @return {@link ResponseEntity<List<AccountResponseDTO>>} list form all account.
     */
    @Operation(
            summary = "Get all",
            description = "Endpoint to get all accounts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All accounts successfully performed!"),
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        return ResponseEntity.ok(this.accountUseCase.getAllAccounts());
    }


    /**
     * {@code GET /} : Request for get account by account numeber
     * <p>
     * Endpoint to get customer information form account.
     *
     * @param accountNumber account number.
     * @return {@link ResponseEntity<AccountResponseDTO>} Object of response where the information and result account.
     */
    @Operation(
            summary = "Get account by account number",
            description = "Endpoint get account by account number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "account successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the get account!")
            }
    )
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDTO> findAccountByAccountNumber(@PathVariable String accountNumber) {
        return this.accountUseCase.findAccountByAccountNumber(accountNumber)
                .map(account ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(account))
                .orElse(ResponseEntity.badRequest().build());
    }


    /**
     * {@code POST /} : Request save account
     * <p>
     * Endpoint to save account
     *
     * @param requestDTO account data request for save .
     * @return {@link ResponseEntity<AccountResponseDTO>} Object of response where the information and result account.
     */
    @Operation(
            summary = "Save account",
            description = "Endpoint to save account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "account save successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the save account!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @PostMapping("/")
    public ResponseEntity<AccountResponseDTO> createAccount(@Valid @RequestBody AccountRequestDTO requestDTO) {
        return this.accountUseCase.saveAccount(requestDTO)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * {@code PUT /} : Request update account
     * <p>
     * Endpoint to update account
     *
     * @param requestDTO account data for save .
     * @param accountId  account number id .
     * @return {@link ResponseEntity<AccountResponseDTO>} Object of response where the information and result account.
     */
    @Operation(
            summary = "Update account",
            description = "Endpoint to update account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "account update successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the update account!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @Valid @RequestBody AccountRequestDTO requestDTO, @PathVariable Long accountId) {
        return this.accountUseCase.updateAccount(requestDTO, accountId)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * {@code DELETE /} : Request delete account
     * <p>
     * Endpoint to delete account
     *
     * @param accountId account id.
     * @return {@link ResponseEntity<Void> } method returns nothing.
     */
    @Operation(
            summary = "Delete account",
            description = "Endpoint to delete account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "account delete successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the delete account!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        this.accountUseCase.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
