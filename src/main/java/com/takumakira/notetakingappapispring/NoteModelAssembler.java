package com.takumakira.notetakingappapispring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class NoteModelAssembler implements RepresentationModelAssembler<Note, EntityModel<Note>> {

  @Override
  public EntityModel<Note> toModel(Note note) {

    return EntityModel.of(note, //
        linkTo(methodOn(NoteController.class).one(note.getId())).withSelfRel(),
        linkTo(methodOn(NoteController.class).all()).withRel("notes"));
  }
}