package uk.zinch.workshop.tradingcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
class TradingCompanyRepositoryTest {

  private static final String TICKER = "TICK";
  private static final String ID = "id";

  @Autowired
  TradingCompanyRepository repo;


  @BeforeEach
  void setup() {
    repo.insert(new TradingCompany(ID, "some company", TICKER))
        .block();
  }

  @Test
  @DisplayName("Should find TradingCompany by ticker")
  void should_find_trading_company_by_ticker() {
    //given
    //when
    Mono<TradingCompany> result = repo.findByTicker(TICKER);
    //then
    StepVerifier.create(result)
                .expectNextMatches(tc -> ID.equals(tc.getId()))
                .verifyComplete();
  }
}
