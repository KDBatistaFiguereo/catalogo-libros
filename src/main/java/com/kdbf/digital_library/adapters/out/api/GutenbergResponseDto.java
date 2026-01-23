package com.kdbf.digital_library.adapters.out.api;

import java.util.List;
import java.util.Set;

public record GutenbergResponseDto(Long count, List<GutenbergBookDto> result) {
  public record GutenbergBookDto(int id, String title, Set<GutenbergPersonDto> authors, List<String> languages) {
  }

  public record GutenbergPersonDto(String name) {
  }
}
