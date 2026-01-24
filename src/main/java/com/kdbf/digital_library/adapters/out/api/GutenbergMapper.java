package com.kdbf.digital_library.adapters.out.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

  public Optional<Book> toDomain(String jsonResponse) {
    try {
      GutenbergResponseDto response = objectMapper.readValue(jsonResponse, GutenbergResponseDto.class);
      if (response == null || response.result().isEmpty() || response.result() == null) {
        return Optional.empty();
      }
      return Optional.of(mapToBook(response.result().get(0)));

    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "identifier", source = "id", qualifiedByName = "createGutenbergId")
  @Mapping(target = "authors", qualifiedByName = "extractAuthorsNames")
  @Mapping(target = "title")
  @Mapping(target = "description", source = "summaries", qualifiedByName = "firstSummaryOnly")
  @Mapping(target = "publisher", ignore = true)
  @Mapping(target = "pageCount", ignore = true)
  @Mapping(target = "copiesInStock", ignore = true)
  @Mapping(target = "language", source = "languages")
  protected abstract Book mapToBook(GutenbergResponseDto.GutenbergBookDto dto);

  @Named("createGutenbergId")
  protected String createGutenbergId(int id) {
    return "GUTENBERG-" + (String.valueOf(id));
  }

  @Named("extractAuthorsNames")
  protected Set<String> extractAuthorsNames(List<GutenbergResponseDto.GutenbergPersonDto> authors) {
    if (authors == null) {
      return Set.of();
    }
    return authors.stream()
        .map(GutenbergResponseDto.GutenbergPersonDto::name)
        .collect(Collectors.toSet());
  }

  @Named("firstSummaryOnly")
  protected String firstSummaryOnly(List<String> summaries) {
    if (summaries == null || summaries.isEmpty()) {
      return "No description";
    }
    return summaries.get(0);
  }

  @Named("firstLanguageOnly")
  protected String firstLanguageOnly(List<String> languages) {
    if (languages == null) {
      return "";
    }
    return languages.get(0);
  }

}
