package com.kdbf.digital_library.adapters.out.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdbf.digital_library.application.domain.model.entity.Book;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class GutenbergMapper {

  private final ObjectMapper objectMapper;

  public GutenbergMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public Optional<Book> toDomain(String body) {
    return Optional.empty();
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "identifier", source = "id", qualifiedByName = "createGutenbergId")
  @Mapping(target = "authors", qualifiedByName = )
  protected abstract Book mapToBook(GutenbergResponseDto.GutenbergBookDto dto);

  @Named("createGutenbergId")
  protected String createGutenbergId(int id) {
    return "GUTENBERG-" + (String.valueOf(id));
  }

  protected Set<String> extractAuthorsNames()

}
