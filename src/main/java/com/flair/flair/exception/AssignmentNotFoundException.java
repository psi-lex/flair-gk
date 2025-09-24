package com.flair.flair.exception;

public class AssignmentNotFoundException extends RuntimeException {

    public AssignmentNotFoundException(Long id) {
        super("Assignment with id " + id + " not found");
    }
}
