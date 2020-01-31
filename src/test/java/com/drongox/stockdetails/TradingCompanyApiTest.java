package com.drongox.stockdetails;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static reactor.core.publisher.Flux.just;

@WebFluxTest(TradingCompanyController.class)
@Import(TradingCompanyRoutes.class)
class TradingCompanyApiTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private TradingCompanyRepository tradingCompanyRepository;

    @Test
    void should_get_all_details() {
        //given
        when(tradingCompanyRepository.findAll())
                .thenReturn(just(new TradingCompany("1", "TEST", "TEST"),
                        new TradingCompany("2","TEST2", "TEST2")));
        //when
        Flux<TradingCompany> response = webTestClient.get()
                .uri("/details")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(TradingCompany.class)
                .getResponseBody();
        //then
        StepVerifier.create(response)
                .expectNextMatches(tc -> tc.getId().equals("1"))
                .expectNextMatches(tc -> tc.getId().equals("2"))
                .verifyComplete();
    }

    @Test
    void should_find_detail_by_ticker() {
        //given
        when(tradingCompanyRepository.findByTicker("TEST"))
                .thenReturn(Mono.just(new TradingCompany("1", "TEST", "TEST")));
        //when
        webTestClient.get()
                .uri("/details/TEST")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
        //then
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("@.ticker").value(IsEqual.equalTo("TEST"));
    }
}
