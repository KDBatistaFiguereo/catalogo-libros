package com.kdbf.digital_library.adapters.out;

import java.net.URI;
import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GutenBergHttpRequestConfig {

  @Bean("gutenbergGet")
  public HttpRequest gutenbergGetRequest(String fullUrl) {
    return HttpRequest.newBuilder()
        .uri(URI.create(fullUrl))
        .GET()
        .build();
  }

}
