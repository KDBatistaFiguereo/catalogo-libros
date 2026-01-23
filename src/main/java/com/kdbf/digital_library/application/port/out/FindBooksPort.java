package com.kdbf.digital_library.application.port.out;

import com.kdbf.digital_library.application.domain.model.entity.Book;

public interface FindBooksPort {
  public Book findById(Long id);
}
