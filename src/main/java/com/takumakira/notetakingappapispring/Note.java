package com.takumakira.notetakingappapispring;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note {

  private @Id @GeneratedValue Long id;
  private String title;
  private String content;
  private String createdAt;

  Note() {}

  Note(String title, String content, String createdAt) {
    this.title = title;
    this.content = content;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getContent() {
    return this.content;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Note))
      return false;
    Note note = (Note) o;
    return Objects.equals(this.id, note.id) && Objects.equals(this.title, note.title) && Objects.equals(this.content, note.content) && Objects.equals(this.createdAt, note.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.title, this.content, this.createdAt);
  }

  @Override
  public String toString() {
    return "Note{" + "id=" + this.id + ", title='" + this.title + '\'' + ", content='" + this.content + '\'' + ", createdAt='" + this.createdAt + '\'' + '}';
  }

}
