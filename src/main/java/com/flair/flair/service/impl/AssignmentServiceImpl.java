package com.flair.flair.service.impl;

import com.flair.flair.enums.AssignmentStatus;
import com.flair.flair.exception.AssignmentNotFoundException;
import com.flair.flair.exception.EmployeeNotFoundException;
import com.flair.flair.jparepository.AssignmentRepository;
import com.flair.flair.jparepository.EmployeeRepository;
import com.flair.flair.persistence.AssignmentEntity;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.service.AssignmentService;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  private static Logger LOGGER = LoggerFactory.getLogger(AssignmentService.class);

  private final AssignmentRepository assignmentRepository;
  private final EmployeeRepository employeeRepository;

  public AssignmentServiceImpl(
      AssignmentRepository assignmentRepository, EmployeeRepository employeeRepository) {
    this.assignmentRepository = assignmentRepository;
    this.employeeRepository = employeeRepository;
  }

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

    assignmentEntity.getEmployees().add(employeeEntity);
    assignmentRepository.save(assignmentEntity);
    LOGGER.info("Added new employee {} to assignment {}", employeeId, assignmentId);
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
}
