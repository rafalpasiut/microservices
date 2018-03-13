package com.rafal.microservices.currencyexchangeservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Environment environment;
    private ExchangeValueRepository repository;

    public CurrencyExchangeController(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public EchangeValue retreiveExchangeValue(@PathVariable String from, @PathVariable String to) {
        EchangeValue echangeValue = repository.findByFromAndTo(from, to);
        echangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return echangeValue;
    }
}
