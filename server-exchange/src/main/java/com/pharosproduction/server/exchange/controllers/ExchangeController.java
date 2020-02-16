package com.pharosproduction.server.exchange.controllers;

import com.pharosproduction.server.exchange.repos.ExchangeRepository;
import com.pharosproduction.server.exchange.models.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exchanges")
public class ExchangeController {

  private final Logger logger = LoggerFactory
    .getLogger(this.getClass());

  @Autowired
  private Environment environment;

  @Autowired
  private ExchangeRepository repository;

  @GetMapping("/{from}/{to}")
  public Exchange show(@PathVariable String from, @PathVariable String to) {
    logger.info("port -> {}", environment.getProperty("local.server.port"));

    return repository.findByCurrencyFromAndCurrencyTo(from, to);
  }
}
