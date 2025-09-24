package com.flair.flair.jparepository;

import com.flair.flair.persistence.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {}
