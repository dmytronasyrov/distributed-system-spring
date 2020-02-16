package com.pharosproduction.currenyexchangeservice.repos;

import com.pharosproduction.currenyexchangeservice.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
  Exchange findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
