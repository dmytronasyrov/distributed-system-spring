package com.pharosproduction.server.conversion.controllers;

import com.pharosproduction.server.conversion.proxies.ExchangeProxy;
import com.pharosproduction.server.conversion.models.Conversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(
  value = "/conversions",
  produces = "application/vnd.pharosproduction.app.v1+json"
)
public class ConversionController {

  private final Logger logger = LoggerFactory
    .getLogger(this.getClass());

  @Autowired
  private Environment environment;

  @Autowired
  private ExchangeProxy proxy;

  @GetMapping("/{from}/{to}/{quantity}")
  public Conversion show(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
//    final Map<String, String> uriVars = new HashMap<>();
//    uriVars.put("from", from);
//    uriVars.put("to", to);
//
//    final ResponseEntity<Conversion> responseEntity = new RestTemplate()
//      .getForEntity(
//        "http://localhost:8001/currency-exchange/from/{from}/to/{to}",
//        Conversion.class,
//        uriVars
//      );
//    final Conversion response = responseEntity.getBody();

    logger.info("port -> {}", environment.getProperty("local.server.port"));

    final Conversion response = proxy.getExchange(from, to);
    response.setQuantity(quantity);
    response.setTotalAmount(quantity.multiply(response.getConversionMultiple()));

    return response;
  }
}
