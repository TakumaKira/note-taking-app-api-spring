package com.takumakira.notetakingappapispring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

  private final NoteModelAssembler assembler;

  NoteController(NoteRepository repository, NoteModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/notes")
  CollectionModel<EntityModel<Note>> all() {
    List<EntityModel<Note>> notes = repository.findAll().stream()
      .map(assembler::toModel)
      .collect(Collectors.toList());
    return CollectionModel.of(notes,
      linkTo(methodOn(NoteController.class).all()).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping("/notes")
  Note newNote(@RequestBody Note newNote) {
    return repository.save(newNote);
  }

  // Single item

  @GetMapping("/notes/{id}")
  EntityModel<Note> one(@PathVariable Long id) {
    Note note = repository.findById(id)
      .orElseThrow(() -> new NoteNotFoundException(id));
    return assembler.toModel(note);
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
