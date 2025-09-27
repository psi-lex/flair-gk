package com.flair.flair.jparepository;

import com.flair.flair.persistence.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {


}
