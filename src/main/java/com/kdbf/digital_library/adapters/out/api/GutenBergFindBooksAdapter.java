package com.kdbf.digital_library.adapters.out.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.kdbf.digital_library.application.domain.model.entity.Book;
import com.kdbf.digital_library.application.port.out.FindBooksPort;

public class GutenBergFindBooksAdapter implements FindBooksPort {

  private final HttpClient httpClient;
  private final GutenbergMapper mapper;
  @Value("${api_url.base_url_gutenberg}")
  private String baseUrl;

  @Autowired
  public GutenBergFindBooksAdapter(HttpClient httpClient, GutenbergMapper mapper) {
    this.httpClient = httpClient;
    this.mapper = mapper;
  }

  @Override
  public Optional<Book> findById(Long id) {
    final String endpoint = "/books";
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + endpoint))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
      return Optional.ofNullable(mapper.toDomain(response.body()));
    } catch (Exception e) {
      return Optional.empty();
    }

  }

}
