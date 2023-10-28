package com.sinha.micro.currencyconversion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    private CurrencyExchangeProxy currencyExchangeProxy;
    private Environment environment;

    @Autowired
    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy, Environment environment) {
        this.currencyExchangeProxy = currencyExchangeProxy;
        this.environment = environment;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> getConversion(@PathVariable Currency from, @PathVariable Currency to, @PathVariable int quantity) {
        CurrencyConversion conv = currencyExchangeProxy.getCurrency(from, to).getBody();
        conv.setQuantity(BigDecimal.valueOf(quantity));
        conv.setTotalCalculatedAmount(BigDecimal.valueOf(quantity).multiply(conv.getConversionMultiple()));
        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version = "v1";

        conv.setEnvironment(conv.getEnvironment() + ":" + port + " " + host + " " + version);

        return  new ResponseEntity<>(conv, HttpStatus.OK);
    }
}
