package com.kdbf.digital_library.application.domain.model.object_value;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.kdbf.digital_library.application.domain.model.exception.NoNameException;
import com.kdbf.digital_library.application.domain.model.exception.NotValidNameException;

import lombok.Getter;

@Getter
public class AuthorName {
  private final String fullName;
  private final String citationName;

  private AuthorName(String fullName, String citationName) {
    this.fullName = fullName;
    this.citationName = citationName;

    if (this.fullName == null || this.citationName == null ||
        this.fullName.isEmpty() || this.citationName.isEmpty()) {
      throw new NoNameException("The author's name can't be null");
    }
    if (!checkLength(this.fullName) || !checkLength(this.citationName)) {
      throw new NotValidNameException("The author's name cant be that short");
    }
  }

  public static AuthorName personName(String name, String surname) {
    if (name == null || surname == null) {
      throw new NoNameException("The author's name can't be null");
    }

    String cleanName = capitalize(name);
    String cleanSurname = capitalize(surname);

    if (cleanName.isEmpty() || cleanSurname.isEmpty()) {
      throw new NoNameException("The author's name cant be empty");
    }
    return new AuthorName(cleanName + " " + cleanSurname, cleanSurname + " " + cleanName);
  }

  public static AuthorName pseudonym(String pseudonym) {
    if (pseudonym == null) {
      throw new NoNameException("The pseudonym cant be null");
    }

    String cleanPseudonym = capitalize(pseudonym);

    if (cleanPseudonym.isEmpty()) {
      throw new NoNameException("The pseudonym cant be null");
    }
    return new AuthorName(cleanPseudonym, cleanPseudonym);
  }

  private static String capitalize(String input) {
    if (input == null || input.isBlank()) {
      return input;
    }

    return Arrays.stream(input.trim().split("\\s+"))
        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
        .collect(Collectors.joining(" "));
  }

  private static boolean checkLength(String input) {
    final int MIN_LENGTH = 3;
    return input.length() > MIN_LENGTH;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
    result = prime * result + ((citationName == null) ? 0 : citationName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuthorName other = (AuthorName) obj;
    if (fullName == null) {
      if (other.fullName != null)
        return false;
    } else if (!fullName.equals(other.fullName))
      return false;
    if (citationName == null) {
      if (other.citationName != null)
        return false;
    } else if (!citationName.equals(other.citationName))
      return false;
    return true;
  }

}
