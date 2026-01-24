package com.kdbf.digital_library.application.domain.model.object_value;

import com.kdbf.digital_library.application.domain.model.exception.BiographyTooLongException;

public record Biography(String bodyText) {
  public Biography {
    if (bodyText == null) {
      bodyText = "";
    }
    if (validateLength(bodyText)) {
      throw new BiographyTooLongException("Biography cant be longer than 3000 characters");
    }
  }

  public static boolean validateLength(String input) {
    final int MAXLENGTH = 3000;
    return input.length() > MAXLENGTH;
  }

}
