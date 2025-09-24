package com.flair.flair.service.impl;

import com.flair.flair.enums.AssignmentStatus;
import com.flair.flair.enums.AssignmentType;
import com.flair.flair.jparepository.AssignmentRepository;
import com.flair.flair.jparepository.EmployeeRepository;
import com.flair.flair.jparepository.NoteRepository;
import com.flair.flair.mapper.NoteMapper;
import com.flair.flair.model.NewNoteTo;
import com.flair.flair.persistence.AssignmentEntity;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.persistence.NoteEntity;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

  @InjectMocks private NoteServiceImpl noteService;

  @Mock private NoteRepository noteRepository;

  @Mock private AssignmentRepository assignmentRepository;

  @Mock private EmployeeRepository employeeRepository;

  @Mock private NoteMapper noteMapper;

  @Test
  void addNewNoteTest() {

    AssignmentEntity assignment = new AssignmentEntity();
    Long assignmentId = 1L;
    assignment.setStatus(AssignmentStatus.STARTED);
    assignment.setType(AssignmentType.SERVICE);
    assignment.setId(assignmentId);

    EmployeeEntity employee = new EmployeeEntity();
    Long employeeId = 2L;
    employee.setId(employeeId);

    NoteEntity noteEntity = new NoteEntity();
    Long noteId = 2L;
    noteEntity.setId(noteId);

    NewNoteTo newNoteTo = new NewNoteTo();

    Mockito.when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));
    Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
    Mockito.when(noteMapper.mapNewNoteToToNoteEntity(newNoteTo)).thenReturn(noteEntity);

    noteService.addNewNote(assignmentId, employeeId, newNoteTo);

    Mockito.verify(noteRepository)
        .save(
            Mockito.argThat(
                note ->
                    note.getAssignment() == assignment
                        && note.getAuthor() == employee
                        && Objects.equals(note.getId(), noteEntity.getId())));
  }

  @Test
  void updateNoteTest() {}

  @Test
  void deleteNoteTest() {}

  @Test
  void displayAllNotesForSpecificAssignmentTest() {}
}
