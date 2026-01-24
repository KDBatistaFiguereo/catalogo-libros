package com.kdbf.digital_library.application.domain.model.object_value;

import java.util.Arrays;
import java.util.Locale;

import com.kdbf.digital_library.application.domain.model.exception.InvalidNationalityException;

public record Nationality(String isoCode) {

  public Nationality {
    if (isoCode == null) {
      throw new InvalidNationalityException("The iso code cant be null");
    }

    isoCode = normalizeIso(isoCode);

    if (!isValidIso(isoCode)) {
      throw new InvalidNationalityException("The iso code is not valid");
    }

    if (isoCode.isBlank() || isoCode.isBlank()) {
      throw new InvalidNationalityException("The iso code cant be empty");
    }
  }

  private static String normalizeIso(String codigoIso) {
    return codigoIso.toUpperCase().trim();
  }

  private static boolean isValidIso(String codigoIso) {
    if (codigoIso.length() < 2 || codigoIso.length() > 2) {
      return false;
    }
    return Arrays.asList(Locale.getISOCountries()).contains(codigoIso);
  }
}
