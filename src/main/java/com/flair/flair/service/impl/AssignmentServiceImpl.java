package com.flair.flair.service.impl;

import com.flair.flair.enums.AssignmentStatus;
import com.flair.flair.exception.AssignmentNotFoundException;
import com.flair.flair.exception.EmployeeNotFoundException;
import com.flair.flair.jparepository.AssignmentRepository;
import com.flair.flair.jparepository.EmployeeRepository;
import com.flair.flair.model.NewAssignmentRequest;
import com.flair.flair.persistence.AssignmentEntity;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.service.AssignmentService;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentService.class);

  private final AssignmentRepository assignmentRepository;
  private final EmployeeRepository employeeRepository;

  public AssignmentServiceImpl(
      AssignmentRepository assignmentRepository, EmployeeRepository employeeRepository) {
    this.assignmentRepository = assignmentRepository;
    this.employeeRepository = employeeRepository;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Override
  public void addEmployeesToService(Long assignmentId, Set<Long> employeesId) {
    AssignmentEntity assignmentEntity =
        this.assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    Set<EmployeeEntity> employeeEntitySet =
        new HashSet<>(employeeRepository.findAllById(employeesId));

    assignmentEntity.getEmployees().addAll(employeeEntitySet);
    assignmentRepository.save(assignmentEntity);
    LOGGER.info("Added new employees to assignment {}", assignmentId);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Override
  public void addEmployeeToService(Long assignmentId, Long employeeId) {
    AssignmentEntity assignmentEntity =
        this.assignmentRepository
            .findById(assignmentId)
            .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    EmployeeEntity employeeEntity =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    if (!assignmentEntity.getStatus().equals(AssignmentStatus.CREATED)) {
      assignmentEntity.setStatus(AssignmentStatus.ASSIGNED);
    }

    assignmentEntity.getEmployees().add(employeeEntity);
    assignmentRepository.save(assignmentEntity);
    LOGGER.info("Added new employee {} to assignment {}", employeeId, assignmentId);
  }

  @Override
  public void addMyselfToService(Long assignmentId) {
    //TODO SecurityContextHolder get my user
    //EmployeeEntity employee = this.employeeRepository.findByEmail()

  }

  @Override
  public void changeAssignmentStatus(Long assignmentId, AssignmentStatus status) {
    AssignmentEntity assignmentEntity =
            this.assignmentRepository
                    .findById(assignmentId)
                    .orElseThrow(() -> new AssignmentNotFoundException(assignmentId));
    assignmentEntity.setStatus(status);
    assignmentRepository.save(assignmentEntity);
    LOGGER.info("Set status {} to assignment {}", status.toString(), assignmentId);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Override
  public void createAssignment(NewAssignmentRequest newAssignmentTo) {

  }
}
