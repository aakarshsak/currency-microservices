package com.sinha.micro.currencyexchange;

import java.util.List;

public interface CurrencyExchangeService {
    List<CurrencyExchange> getAllCurrencyExchange();
    CurrencyExchange getCurrencyExchangeFromAndTo(Currency from, Currency to);
}
