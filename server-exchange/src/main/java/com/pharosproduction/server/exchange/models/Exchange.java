package com.pharosproduction.server.exchange.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "exchanges")
public class Exchange {

  @Id
  @GeneratedValue
  private Long id;
  private String currencyFrom;
  private String currencyTo;
  private BigDecimal conversionMultiple;

  public Exchange() {}

  public Exchange(Long id, String currencyFrom, String currencyTo, BigDecimal conversionMultiple) {
    this.id = id;
    this.currencyFrom = currencyFrom;
    this.currencyTo = currencyTo;
    this.conversionMultiple = conversionMultiple;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getConversionMultiple() {
    return conversionMultiple;
  }

  public void setConversionMultiple(BigDecimal conversionMultiple) {
    this.conversionMultiple = conversionMultiple;
  }

  public String getCurrencyFrom() {
    return currencyFrom;
  }

  public void setCurrencyFrom(String currencyFrom) {
    this.currencyFrom = currencyFrom;
  }

  public String getCurrencyTo() {
    return currencyTo;
  }

  public void setCurrencyTo(String currencyTo) {
    this.currencyTo = currencyTo;
  }
}
