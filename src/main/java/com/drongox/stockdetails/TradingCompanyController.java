package com.drongox.stockdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Controller
@RequiredArgsConstructor
public class TradingCompanyController {

    private final TradingCompanyRepository repo;

    public Mono<ServerResponse> getAllTradingCompanies(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repo.findAll(), TradingCompany.class);
    }

    public Mono<ServerResponse> getTradingCompanyByTicker(ServerRequest serverRequest) {
        return repo.findByTicker(serverRequest.pathVariable("ticker"))
                .flatMap(found -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(found), TradingCompany.class))
                .switchIfEmpty(notFound().build());
    }
}
