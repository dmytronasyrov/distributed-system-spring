package com.pharosproduction.server.exchange.repos;

import com.pharosproduction.server.exchange.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
  Exchange findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
