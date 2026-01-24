package com.kdbf.digital_library.application.domain.model.exception;

public class BiographyTooLongException extends RuntimeException {
  public BiographyTooLongException(String message) {
    super(message);
  }
}
