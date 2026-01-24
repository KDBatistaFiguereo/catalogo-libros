package com.kdbf.digital_library.application.domain.model.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.UUID;

import com.kdbf.digital_library.application.domain.model.exception.SuspiciousAgeException;
import com.kdbf.digital_library.application.domain.model.object_value.AuthorName;
import com.kdbf.digital_library.application.domain.model.object_value.Biography;
import com.kdbf.digital_library.application.domain.model.object_value.Nationality;

public class Author {
  private UUID id;
  private AuthorName authorName;
  private AuthorName alternativeName;
  private LocalDate birthDate;
  private LocalDate deathDate;
  private Biography biography;
  private Nationality nationality;
  private Set<Book> books;

  private Author(UUID id, AuthorName authorName, AuthorName alternativeName, LocalDate birthDate, LocalDate deathDate,
      Biography biography, Nationality nationality, Set<Book> books) {
    this.id = id;
    this.authorName = authorName;
    this.alternativeName = alternativeName;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.biography = biography;
    this.nationality = nationality;
    this.books = books;
  }

  public static Author createAuthor(
      AuthorName authorName,
      AuthorName alternativeName,
      LocalDate birthDate,
      LocalDate deathDate,
      Biography biography,
      Nationality nationality,
      Set<Book> books) {

    if (!validateBirthDeath(birthDate, deathDate)) {
      throw new IllegalStateException("Not valid dates");
    }

    UUID id = UUID.randomUUID();

    return new Author(
        id,
        authorName,
        alternativeName,
        birthDate,
        deathDate,
        biography,
        nationality,
        books);
  }

  private static boolean validateBirthDeath(LocalDate birthDate, LocalDate deathDate) {
    if (birthDate == null || deathDate == null) {
      return true;
    }

    long age = ChronoUnit.YEARS.between(birthDate, deathDate);
    if (age <= 0) { // death before birth or died the same day/year
      return false;
    }

    if (age >= 150) { // unlikely age
      throw new SuspiciousAgeException("Age too old. Verify.");
    }

    if (age < 4) { // unlikely age
      throw new SuspiciousAgeException("Age too young. Verify");
    }

    return true;
  }

  public UUID getId() {
    return id;
  }

  public AuthorName getAuthorName() {
    return authorName;
  }

  public AuthorName getAlternativeName() {
    return alternativeName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public LocalDate getDeathDate() {
    return deathDate;
  }

  public Biography getBiography() {
    return biography;
  }

  public Nationality getNationality() {
    return nationality;
  }

  public Set<Book> getBooks() {
    return books;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Author other = (Author) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Author [id=" + id + ", authorName=" + authorName + ", alternativeName=" + alternativeName + ", birthDate="
        + birthDate + ", deathDate=" + deathDate + ", biography=" + biography + ", nationality=" + nationality
        + ", books=" + books + "]";
  }

}
