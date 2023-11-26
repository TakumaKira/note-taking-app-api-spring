package com.takumakira.notetakingappapispring;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

  private final NoteRepository repository;

  NoteController(NoteRepository repository) {
    this.repository = repository;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/notes")
  List<Note> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/notes")
  Note newNote(@RequestBody Note newNote) {
    return repository.save(newNote);
  }

  // Single item

  @GetMapping("/notes/{id}")
  Note one(@PathVariable Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new NoteNotFoundException(id));
  }

  @PutMapping("/notes/{id}")
  Note replaceNote(@RequestBody Note newNote, @PathVariable Long id) {
    return repository.findById(id)
      .map(note -> {
        note.setTitle(newNote.getTitle());
        note.setContent(newNote.getContent());
        return repository.save(note);
      })
      .orElseGet(() -> {
        newNote.setId(id);
        return repository.save(newNote);
      });
  }

  @DeleteMapping("/notes/{id}")
  void deleteNote(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
