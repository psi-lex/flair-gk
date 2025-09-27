package com.flair.flair.jparepository;

import com.flair.flair.persistence.EmployeeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  Optional<EmployeeEntity> findByEmail(String email);
}
