package com.sinha.micro.currencyexchange;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Value("${spring.datasource.url}")
    private String url;

    Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> getCurrency(@PathVariable Currency from, @PathVariable Currency to) {
        logger.info("My info: " + url);
        return new ResponseEntity<>(currencyExchangeService.getCurrencyExchangeFromAndTo(from, to), HttpStatus.OK);
    }
}
