package com.flair.flair.service;

import com.flair.flair.model.NewNoteTo;
import com.flair.flair.model.NoteTo;
import com.flair.flair.model.UpdateNoteTo;
import java.util.Set;

public interface NoteService {

  void addNewNote(Long assignmentId, Long employeeId, NewNoteTo newNoteTo);

  void updateNote(Long assignmentId, Long employeeId, UpdateNoteTo updateNoteTo);

  void deleteNote(Long assignmentId, Long noteId);

  Set<NoteTo> displayAllNotes(Long assignmentId);
}
