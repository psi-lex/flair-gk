package com.flair.flair.validator;

import com.flair.flair.exception.validation.assignment.AssignmentValidationException;
import com.flair.flair.model.NewAssignmentRequest;
import org.springframework.stereotype.Component;

@Component
public class AssignmentValidator {

  public void validateAssignment(NewAssignmentRequest assignmentRequest) {
    if (assignmentRequest == null) {
      throw new AssignmentValidationException("Assignment cannot be null");
    }
    if (assignmentRequest.getCustomerTo() == null) {
      throw new AssignmentValidationException("Customer in assignment cannot be null");
    }
  }

  private void validateEmailAndNumber() {}
}
