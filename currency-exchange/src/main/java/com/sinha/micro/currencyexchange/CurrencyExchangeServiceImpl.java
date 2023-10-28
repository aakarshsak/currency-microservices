package com.sinha.micro.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private CurrencyRepository currencyRepository;
    private Environment environment;

    @Autowired
    public CurrencyExchangeServiceImpl(CurrencyRepository currencyRepository, Environment environment) {
        this.currencyRepository = currencyRepository;
        this.environment = environment;
    }

    @Override
    public List<CurrencyExchange> getAllCurrencyExchange() {
        return currencyRepository.findAll();
    }

    @Override
    public CurrencyExchange getCurrencyExchangeFromAndTo(Currency from, Currency to) {
        CurrencyExchange currencyExchange = currencyRepository.findByFromAndTo(from, to);

        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version = "v1";

        currencyExchange.setEnvironment(port + " " + host + " " + version);

        return currencyExchange;
    }
}
