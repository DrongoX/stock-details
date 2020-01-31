package com.drongox.stockdetails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class TradingCompanyRepositoryTest {

	@Autowired
	TradingCompanyRepository tradingCompanyRepository;

	@Test
	void should_find_by_ticker() {
		tradingCompanyRepository.insert(new TradingCompany(null, "Test company", "TEST")).block();

		Mono<TradingCompany> found = tradingCompanyRepository.findByTicker("TEST");

		StepVerifier.create(found)
				.consumeNextWith(company -> assertThat(company.getTicker()).isEqualTo("TEST"))
				.verifyComplete();
	}
}
