package com.pharosproduction.server.conversion.proxies;

import com.pharosproduction.server.conversion.models.Conversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gateway")
@RibbonClient(name = "exchange")
public interface ExchangeProxy {
  @GetMapping("/server-exchange/exchanges/{from}/{to}")
  Conversion getExchange(@PathVariable String from, @PathVariable String to);
}
