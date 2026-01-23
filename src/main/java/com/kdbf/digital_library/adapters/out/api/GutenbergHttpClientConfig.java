package com.kdbf.digital_library.adapters.out.api;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GutenbergHttpClientConfig {

  @Bean
  public HttpClient gutenbergHttpClient() {
    return HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(20))
        .build();
  }
}
