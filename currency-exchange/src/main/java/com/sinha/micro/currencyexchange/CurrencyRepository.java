package com.sinha.micro.currencyexchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(Currency from, Currency to);
}
