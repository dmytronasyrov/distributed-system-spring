package com.pharosproduction.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

  @Autowired
  private Configuration configuration;

  @GetMapping("/limits")
  @HystrixCommand(fallbackMethod = "configurationFault")
  public LimitsConfiguration retrieveLimitsFromConfigurations() {
    throw new RuntimeException("Not available");
  }

  private LimitsConfiguration configurationFault() {
    return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
  }
}
