package com.pharosproduction.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

  @Autowired
  private CurrencyExchangeServiceProxy proxy;

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    final Map<String, String> uriVars = new HashMap<>();
    uriVars.put("from", from);
    uriVars.put("to", to);

    final ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
      .getForEntity(
        "http://localhost:8001/currency-exchange/from/{from}/to/{to}",
        CurrencyConversion.class,
        uriVars
      );
    final CurrencyConversion response = responseEntity.getBody();

    return new CurrencyConversion(
      response.getId(),
      from,
      to,
      response.getConversionMultiple(),
      quantity,
      quantity.multiply(response.getConversionMultiple()),
      response.getPort());
  }

  @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
    final CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(
      response.getId(),
      from,
      to,
      response.getConversionMultiple(),
      quantity,
      quantity.multiply(response.getConversionMultiple()),
      response.getPort());
  }
}
