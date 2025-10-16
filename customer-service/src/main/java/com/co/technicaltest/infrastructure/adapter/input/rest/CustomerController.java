package com.co.technicaltest.infrastructure.adapter.input.rest;

import com.co.technicaltest.application.dto.CustomerLoginResponseDTO;
import com.co.technicaltest.application.dto.CustomerRequestDTO;
import com.co.technicaltest.application.dto.CustomerResponseDTO;
import com.co.technicaltest.application.mapper.CustomerDTOMapper;
import com.co.technicaltest.domain.model.Customer;
import com.co.technicaltest.domain.port.input.CustomerUseCase;
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

import java.util.UUID;

/**
 * Customer management controller.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Clientes" , description = "API para clientes")
public class CustomerController {


    private final CustomerUseCase customerUseCase;

    private final CustomerDTOMapper customerDTOMapper;


    public CustomerController(CustomerUseCase customerUseCase,
                              CustomerDTOMapper customerDTOMapper) {
        this.customerUseCase = customerUseCase;
        this.customerDTOMapper = customerDTOMapper;
    }

    /**
     * {@code GET /} : Get all customer
     * <p>
     * Endpoint get all customer.
     *
     * @return {@link ResponseEntity<List<CustomerResponseDTO>>} list form all customer.
     */
    @Operation(
            summary = "Get all",
            description = "Endpoint to get all customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All customer successfully performed!"),
            }
    )
    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(this.customerUseCase.getAllCustomer());
    }


    /**
     * {@code GET /} : Request for get data login for username
     *
     * Endpoint to get customer information form username.
     *
     * @param username  customer username .
     * @return {@link ResponseEntity<CustomerLoginResponseDTO>} Object of response where the information and result customer.
     */
    @Operation(
            summary = "Get customer by username",
            description = "Endpoint get customer by username",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the get customer!")
            }
    )
    @GetMapping("/usuario")
    public ResponseEntity<CustomerLoginResponseDTO> getCustomerByUsername(@RequestParam(name = "username") String username) {
        return this.customerUseCase.findCustomerByUsername(username)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * {@code GET /} : Request get customer by id
     *
     * Endpoint to obstains customer by id
     *
     * @param customerId  customer uuid .
     * @return {@link ResponseEntity<CustomerResponseDTO> } Object of response where the information and result customer.
     */
    @Operation(
            summary = "get customer by id",
            description = "Endpoint to get customer by uuid",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the customer obtains!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByCustomerId(@PathVariable UUID customerId) {
        return this.customerUseCase.findUserByCustomerId(customerId)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }


    /**
     * {@code POST /} : Request save customer
     *
     * Endpoint to save customer
     *
     * @param customerRequestDTO  customer data for save .
     * @return {@link ResponseEntity<CustomerResponseDTO>} Object of response where the information and result customer.
     */
    @Operation(
            summary = "Save customer",
            description = "Endpoint to save customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer save successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the save customer!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @PostMapping("/")
    public ResponseEntity<CustomerResponseDTO> saveCustomer(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        Customer customer =
                this.customerDTOMapper.customerRequestDTOCustomer(customerRequestDTO);
        return this.customerUseCase.saveCustomer(customer)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }


    /**
     * {@code PUT /} : Request update customer
     *
     * Endpoint to update customer
     *
     * @param customerRequestDTO  customer data for save .
     * @param customerId  customer id .
     * @return {@link ResponseEntity<CustomerResponseDTO>} Object of response where the information and result customer.
     */
    @Operation(
            summary = "Update customer",
            description = "Endpoint to update customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer update successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the update customer!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @Valid @RequestBody CustomerRequestDTO customerRequestDTO, @PathVariable UUID customerId) {
        Customer customer =
                this.customerDTOMapper.customerRequestDTOCustomer(customerRequestDTO);
        return this.customerUseCase.updateCustomer(customer, customerId)
                .map(result ->
                        ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(result))
                .orElse(ResponseEntity.badRequest().build());
    }

    /**
     * {@code DELETE /} : Request delete customer
     *
     * Endpoint to delte customer
     *
     * @param customerId  customer uuid.
     * @return {@link ResponseEntity<Void> } method returns nothing.
     */
    @Operation(
            summary = "Delte customer",
            description = "Endpoint to delete customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "customer delete successfully performed!"),
                    @ApiResponse(responseCode = "400", description = "Error in the delete customer!"),
                    @ApiResponse(responseCode = "412", description = "Error in the validation of the sent parameters!")
            }
    )
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID customerId) {
        this.customerUseCase.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
