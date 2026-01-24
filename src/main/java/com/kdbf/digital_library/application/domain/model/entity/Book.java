package com.kdbf.digital_library.application.domain.model.entity;

import java.util.Set;
import java.util.UUID;

import com.kdbf.digital_library.application.domain.model.object_value.Isbn;

import lombok.Getter;

@Getter
public class Book {

  UUID id;
  Isbn isbn;
  String identifier; // An alternative identifier to ISBN
  Set<Author> authors; // a book can be done by many people
  String title;
  String description;
  String publisher;
  int pageCount;
  String language;
  int copiesInStock; // Amount of books
  int downloads;

  public Book(Isbn isbn, String identifier, Set<Author> authors, String title, String description, String publisher,
      int pageCount, String language, int copiesInStock, int downloads) {
    this.isbn = isbn;
    this.identifier = identifier;
    this.authors = authors;
    this.title = title;
    this.description = description;
    this.publisher = publisher;
    this.pageCount = pageCount;
    this.language = language;
    this.copiesInStock = copiesInStock;
    this.downloads = downloads;
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
    Book other = (Book) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", identifier=" + identifier + ", authors=" + authors + ", title=" + title
        + ", description=" + description + ", publisher=" + publisher + ", pageCount=" + pageCount + "]";
  }

}
