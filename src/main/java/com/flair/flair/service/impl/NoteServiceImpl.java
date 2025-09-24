package com.flair.flair.service.impl;

import com.flair.flair.exception.AssignmentNotFoundException;
import com.flair.flair.exception.EmployeeNotFoundException;
import com.flair.flair.exception.NoteNotFoundException;
import com.flair.flair.jparepository.AssignmentRepository;
import com.flair.flair.jparepository.EmployeeRepository;
import com.flair.flair.jparepository.NoteRepository;
import com.flair.flair.mapper.NoteMapper;
import com.flair.flair.model.NewNoteTo;
import com.flair.flair.model.NoteTo;
import com.flair.flair.model.UpdateNoteTo;
import com.flair.flair.persistence.AssignmentEntity;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.persistence.NoteEntity;
import com.flair.flair.service.NoteService;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);

  // repositories
  private final NoteRepository noteRepository;
  private final AssignmentRepository assignmentRepository;
  private final EmployeeRepository employeeRepository;

  // mappers
  private final NoteMapper noteMapper;

  public NoteServiceImpl(
      NoteRepository noteRepository,
      AssignmentRepository assignmentRepository,
      EmployeeRepository employeeRepository,
      NoteMapper noteMapper) {
    this.noteRepository = noteRepository;
    this.assignmentRepository = assignmentRepository;
    this.employeeRepository = employeeRepository;
    this.noteMapper = noteMapper;
  }

  @Override
  public void addNewNote(Long assignmentId, Long employeeId, NewNoteTo newNoteTo) {

    AssignmentEntity assignment =
        assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    EmployeeEntity employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    NoteEntity noteEntity = this.noteMapper.mapNewNoteToToNoteEntity(newNoteTo);
    noteEntity.setAssignment(assignment);
    noteEntity.setAuthor(employee);
    noteRepository.save(noteEntity);
    LOGGER.info("New note with id {} added to DB", noteEntity.getId());
  }

  @Override
  public void updateNote(Long assignmentId, Long employeeId, UpdateNoteTo updateNoteTo) {
    AssignmentEntity assignment =
        assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    EmployeeEntity employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    NoteEntity noteEntity =
        noteRepository
            .findById(updateNoteTo.getNoteId())
            .orElseThrow(() -> new NoteNotFoundException(updateNoteTo.getNoteId()));

    noteEntity.setContent(updateNoteTo.getContent());
    noteRepository.save(noteEntity);
    LOGGER.info("Note with id {} edited and added to DB", noteEntity.getId());
  }

  @Override
  public void deleteNote(Long assignmentId, Long noteId) {
    AssignmentEntity assignment =
        assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    NoteEntity noteEntity =
        noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException(noteId));

    if (!Objects.equals(noteEntity.getAssignment().getId(), assignment.getId())) {
      throw new IllegalStateException("This note does not belong to this assignment!");
    }
    noteRepository.delete(noteEntity);
    LOGGER.info("Note with id {} deleted", noteEntity.getId());
  }

  /**
   * Display all notes for specific assignment
   *
   * @return notes
   */
  @Override
  public Set<NoteTo> displayAllNotesForSpecificAssignment(Long assignmentId) {
    AssignmentEntity assignment =
        assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    return assignment.getNoteEntitySet().stream()
        .map(noteMapper::mapNoteEntityToNoteTo)
        .collect(Collectors.toSet());
  }

  /**
   * Display all notes overall in database
   *
   * @return notes
   */

  // @PreAuthorize("hasRole('ADMIN')")
  @Override
  public Set<NoteTo> displayAllNotes() {
    return noteRepository.findAll().stream()
        .map(noteMapper::mapNoteEntityToNoteTo)
        .collect(Collectors.toSet());
  }
}
