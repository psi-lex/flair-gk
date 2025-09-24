package com.flair.flair.service;

import com.flair.flair.enums.AssignmentStatus;
import java.util.Set;

public interface AssignmentService {

  void addEmployeesToService(Long assignmentId, Set<Long> employeesId);

  void addEmployeeToService(Long assignmentId, Long employeesId);

  void changeAssignmentStatus(Long assignmentId, AssignmentStatus status);
}
