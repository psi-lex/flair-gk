package com.flair.flair.persistence;

import com.flair.flair.enums.AssignmentStatus;
import com.flair.flair.enums.AssignmentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {

    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<NoteEntity> noteEntitySet;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "assignments")
    private Set<EmployeeEntity> employees = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;
    @Enumerated(EnumType.STRING)
    private AssignmentType type;
}
