package com.kdbf.digital_library.application.domain.model.exception;

public class SuspiciousAgeException extends RuntimeException {
  public SuspiciousAgeException(String message) {
    super(message);
  }
}
