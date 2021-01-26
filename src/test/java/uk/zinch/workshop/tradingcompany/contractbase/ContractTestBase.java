package uk.zinch.workshop.tradingcompany.contractbase;

import uk.zinch.workshop.tradingcompany.TradingCompany;
import uk.zinch.workshop.tradingcompany.TradingCompanyRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractTestBase {

  @LocalServerPort
  private int port;

  @MockBean
  TradingCompanyRepository repo;


  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost:" + port;

    when(repo.findByTicker(any()))
        .thenAnswer(ic -> {
          String ticker = ic.getArgument(0);
          switch (ticker) {
            case "NOTFOUND":
              return Mono.empty();
            case "ERROR":
              return Mono.error(IllegalArgumentException::new);
            default:
              return Mono.just(new TradingCompany("ID", "desc", "TEST"));
          }
        });
  }
}
