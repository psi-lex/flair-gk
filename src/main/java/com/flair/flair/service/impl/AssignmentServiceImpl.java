package com.flair.flair.service.impl;

import com.flair.flair.enums.AssignmentStatus;
import com.flair.flair.exception.AssignmentNotFoundException;
import com.flair.flair.exception.EmployeeNotFoundException;
import com.flair.flair.jparepository.AssignmentRepository;
import com.flair.flair.jparepository.EmployeeRepository;
import com.flair.flair.mapper.AssignmentMapper;
import com.flair.flair.model.NewAssignmentRequest;
import com.flair.flair.persistence.AssignmentEntity;
import com.flair.flair.persistence.EmployeeEntity;
import com.flair.flair.service.AssignmentService;
import com.flair.flair.validator.AddressValidator;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {

  // logger
  private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentService.class);

  // repositories
  private final AssignmentRepository assignmentRepository;
  private final EmployeeRepository employeeRepository;

  // mappers
  private final AssignmentMapper assignmentMapper;

  // validators
  private final AddressValidator addressValidator;

  public AssignmentServiceImpl(
      AssignmentRepository assignmentRepository,
      EmployeeRepository employeeRepository,
      AssignmentMapper assignmentMapper,
      AddressValidator addressValidator) {
    this.assignmentRepository = assignmentRepository;
    this.employeeRepository = employeeRepository;
    this.assignmentMapper = assignmentMapper;
    this.addressValidator = addressValidator;
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
    // TODO SecurityContextHolder get my user
    // EmployeeEntity employee = this.employeeRepository.findByEmail()

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

  // TODO
  // @PreAuthorize("hasRole('ADMIN')")
  @Override
  public void createAssignment(NewAssignmentRequest newAssignmentTo) {
    addressValidator.validateAddress(newAssignmentTo.getAddress());
    AssignmentEntity assignment =
        assignmentMapper.mapNewAssignmentRequestToAssignmentEntity(newAssignmentTo);
    assignment.setStatus(AssignmentStatus.CREATED);
    Set<EmployeeEntity> employeeEntitySet =
        new HashSet<>(this.employeeRepository.findAllById(newAssignmentTo.getEmployeesId()));
    assignment.setEmployees(employeeEntitySet);
    assignmentRepository.save(assignment);
    LOGGER.info("Created assignment with id {}", assignment.getId());
  }
}
