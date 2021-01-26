package uk.zinch.workshop.tradingcompany;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {
  @Bean
  public RouterFunction<ServerResponse> tradingCompanyRoutes(TradingCompanyController controller) {
    return route()
        .GET("/trading-companies/{ticker}", controller::findByTicker)
        .build();
  }
}
