package com.sinha.micro.currencyexchange;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> getCurrency(@PathVariable Currency from, @PathVariable Currency to) {
        return new ResponseEntity<>(currencyExchangeService.getCurrencyExchangeFromAndTo(from, to), HttpStatus.OK);
    }
}
