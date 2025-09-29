package com.co.technicaltest.auth_service.infrastructure.adapter.output.client;


import com.co.technicaltest.auth_service.domain.model.Customer;
import com.co.technicaltest.auth_service.application.dto.RegisterRequestDTO;
import com.co.technicaltest.auth_service.application.dto.RegisterResponseDTO;
import com.co.technicaltest.auth_service.infrastructure.config.LoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feing client for HTTP communication with customer service.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@FeignClient(name = "customer-service")
@LoadBalancerClient(name = "customer-service", configuration = LoadBalancerConfig.class)
public interface ClientFeign {



    @GetMapping("/api/v1/clientes/usuario")
    RegisterRequestDTO findByUsernameOnCustomerService(@RequestParam(name = "username") String username);


    @PostMapping("/api/v1/clientes/")
    RegisterResponseDTO saveOnCustomerService(@RequestBody Customer customer);
}
