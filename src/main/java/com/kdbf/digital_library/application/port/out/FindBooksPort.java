package com.kdbf.digital_library.application.port.out;

import java.util.Optional;

import com.kdbf.digital_library.application.domain.model.entity.Book;

public interface FindBooksPort {
  public Optional<Book> findById(Long id);
}
