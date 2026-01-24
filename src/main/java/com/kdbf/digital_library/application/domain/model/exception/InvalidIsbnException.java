package com.kdbf.digital_library.application.domain.model.exception;

public class InvalidIsbnException extends RuntimeException {
  public InvalidIsbnException(String message) {
    super(message);
  }

}
