package com.drongox.stockdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TradingCompaniesInsertor implements CommandLineRunner {
    private final TradingCompanyRepository tradingCompanyRepository;

    @Override
    public void run(String... args) {
        List<TradingCompany> fakeCompanies = List.of(
                new TradingCompany("1", "Goolgate", "GOOL"),
                new TradingCompany("2", "Mamazone", "MAMA"),
                new TradingCompany("3", "MacroHard", "MAHD"),
                new TradingCompany("4", "Happen", "HAPP"),
                new TradingCompany("5", "Real Google", "GOOG")
        );
        tradingCompanyRepository.insert(fakeCompanies)
                .blockLast(Duration.ofSeconds(1));
    }
}
