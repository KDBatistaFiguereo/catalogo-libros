package com.kdbf.digital_library.application.domain.model.exception;

public class InvalidNationalityException extends RuntimeException {
  public InvalidNationalityException(String message) {
    super(message);
  }
}
