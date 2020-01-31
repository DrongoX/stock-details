package com.drongox.stockdetails;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TradingCompanyRoutes {
    @Bean
    RouterFunction<ServerResponse> detailsRoutes(TradingCompanyController controller) {
        return route()
                .GET("/details", controller::getAllTradingCompanies)
                .GET("/details/{ticker}", controller::getTradingCompanyByTicker)
                .build();
    }
}
