package com.flair.flair.persistence;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "second_name", nullable = true)
  private String secondName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", unique = true)
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private AddressEntity address;

  @ManyToMany
  @JoinTable(
      name = "employee_assignment",
      joinColumns = @JoinColumn(name = "employee_id"),
      inverseJoinColumns = @JoinColumn(name = "assignment_id"))
  private Set<AssignmentEntity> assignments = new HashSet<>();

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<NoteEntity> notes = new HashSet<>();
}
