package com.kdbf.digital_library.application.domain.model.object_value;

import com.kdbf.digital_library.application.domain.model.exception.InvalidIsbnException;

import lombok.Getter;

@Getter
public class Isbn {

  private String isbnCode;

  private enum IsbnType {
    ISBN_10(10),
    ISBN_13(13);

    private final int length;

    IsbnType(int length) {
      this.length = length;
    }
  }

  private Isbn(String isbnCode) {
    this.isbnCode = isbnCode;
  }

  public static Isbn isbn10(String isbnCode10) {
    isbnCode10 = normalizeIsbn(isbnCode10);
    if (!validateIsbnLength(isbnCode10, IsbnType.ISBN_10)) {
      throw new InvalidIsbnException("The code is not in ISBN10 format");
    }

    return new Isbn(isbnCode10);
  }

  public static Isbn isbn13(String isbnCode13) {
    isbnCode13 = normalizeIsbn(isbnCode13);
    if (!validateIsbnLength(isbnCode13, IsbnType.ISBN_13)) {
      throw new InvalidIsbnException("The code is not in ISBN13 format");
    }

    return new Isbn(isbnCode13);
  }

  private static boolean validateIsbnLength(String isbnCode, IsbnType isbn) {
    if (isbnCode == null || isbnCode.isEmpty()) {
      return false;
    }
    if (isbnCode.length() == isbn.length) {
      return true;
    } else {
      return false;
    }
  }

  private static String normalizeIsbn(String isbnCode) {
    String newIsbn = isbnCode.trim().replace("-", "");
    return newIsbn;
  }

  // public String getIsbnWithHyphens() {
  // return isbnCode;
  //
  // }
  //
}
