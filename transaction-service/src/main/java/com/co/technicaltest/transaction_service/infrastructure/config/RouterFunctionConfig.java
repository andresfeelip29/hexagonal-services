package com.co.technicaltest.transaction_service.infrastructure.config;

import com.co.technicaltest.transaction_service.infrastructure.adapter.input.rest.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Router configuration for reactive URL.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
@Configuration
public class RouterFunctionConfig {


    @Bean
    public RouterFunction<ServerResponse> routes(TransactionHandler handler) {
        return RouterFunctions.route(RequestPredicates.GET(FIND_ALL_URL), handler::findAllTransaction)
                .andRoute(RequestPredicates.GET(BY_ID_URL), handler::findTransactionById)
                .andRoute(RequestPredicates.POST(SAVE_URL), handler::saveTransaction)
                .andRoute(RequestPredicates.GET(REPORT_URL), handler::filterTransactionForRageDateAndClientId);
    }


    private final static String BASE_URL = "/api/v1/movimientos";
    private final static String BY_ID_URL = BASE_URL + "/{id}";
    private final static String SAVE_URL = BASE_URL + "/";
    private final static String FIND_ALL_URL = BASE_URL + "/";
    private final static String REPORT_URL = BASE_URL + "/reporte/";

}
