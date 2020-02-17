package com.pharosproduction.server.conversion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  private static final Contact DEFAULT_CONTACT = new Contact(
    "Dmytro Nasyrov",
    "https://pharosproduction.com",
    "dmytro@pharosproduction.com"
  );
  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(List.of("application/json"));

  @Bean
  public Docket apiV1() {
    final ApiInfo apiInfo = new ApiInfo(
      "Api title",
      "Api description",
      "1.0.0",
      "urn:tos",
      DEFAULT_CONTACT,
      "Apache 2.0",
      "https://pharosproduction.com",
      List.of()
    );

    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("v1")
      .select()
      .apis(p -> {
        if (p.produces() == null || p.produces().isEmpty()) return false;

        for (MediaType mediaType : p.produces()) {
          if (mediaType.toString().equals("application/vnd.pharosproduction.app.v1+json")) {
            return true;
          }
        }

        return false;
      })
      .paths(PathSelectors.any())
      .build()
      .produces(Collections.singleton("application/vnd.pharosproduction.app.v1+json"))
      .apiInfo(apiInfo)
      .produces(DEFAULT_PRODUCES_AND_CONSUMES)
      .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
  }
}
