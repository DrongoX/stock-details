package uk.zinch.workshop.tradingcompany;

import lombok.RequiredArgsConstructor;
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

  public Mono<ServerResponse> findByTicker(ServerRequest request) {
    return repo.findByTicker(request.pathVariable("ticker"))
               .flatMap(tc -> ok().bodyValue(tc))
               .switchIfEmpty(notFound().build());
  }
}
