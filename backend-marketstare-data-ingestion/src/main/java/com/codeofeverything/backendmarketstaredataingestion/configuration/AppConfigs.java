package com.codeofeverything.backendmarketstaredataingestion.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfigs {
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  ObjectMapper objectMapper(){
    return new ObjectMapper();
  }
}
