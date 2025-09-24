package com.flair.flair.controller;

import com.flair.flair.model.NoteTo;
import com.flair.flair.service.impl.NoteServiceImpl;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {

  private final NoteServiceImpl noteService;

  public NoteController(NoteServiceImpl noteService) {
    this.noteService = noteService;
  }

  @GetMapping("/all/{id}")
  public ResponseEntity<Set<NoteTo>> getAllNotesForSpecificAssignment(@PathVariable Long id) {
    return ResponseEntity.ok(this.noteService.displayAllNotesForSpecificAssignment(id));
  }

  @GetMapping("/all")
  public ResponseEntity<Set<NoteTo>> getAllNotes() {
    return ResponseEntity.ok(this.noteService.displayAllNotes());
  }
}
