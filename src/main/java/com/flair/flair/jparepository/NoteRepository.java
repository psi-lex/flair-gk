package com.flair.flair.jparepository;

import com.flair.flair.persistence.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
