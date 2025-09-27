package com.flair.flair.controller.advice;

import com.flair.flair.exception.AssignmentNotFoundException;
import com.flair.flair.exception.EmployeeNotFoundException;
import com.flair.flair.exception.NoteNotFoundException;
import com.flair.flair.exception.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(exception = AssignmentNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleAssignmentNotFoundException(
      AssignmentNotFoundException exception, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Assignment not found.");
    body.put("message", exception.getMessage());
    body.put("path", request.getDescription(false).replace("uri=", ""));

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(exception = EmployeeNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleEmployeeNotFoundException(
      EmployeeNotFoundException exception, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Employee not found.");
    body.put("message", exception.getMessage());
    body.put("path", request.getDescription(false).replace("uri=", ""));

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(exception = NoteNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNoteNotFoundException(
      NoteNotFoundException exception, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Note not found.");
    body.put("message", exception.getMessage());
    body.put("path", request.getDescription(false).replace("uri=", ""));

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(exception = ValidationException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(
      ValidationException exception, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "Validation exception");
    body.put("message", exception.getMessage());
    body.put("path", request.getDescription(false).replace("uri=", ""));
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /** Handle general exception */
  @ExceptionHandler(exception = Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneralException(
      Exception exception, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "General exception.");
    body.put("message", exception.getMessage());
    body.put("path", request.getDescription(false).replace("uri=", ""));

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
