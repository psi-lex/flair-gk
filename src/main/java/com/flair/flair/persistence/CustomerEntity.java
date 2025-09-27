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
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "customer_name", nullable = false)
  private String name;

  @Column(name = "mobile_number", nullable = false)
  private String mobileNumber;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @OneToMany(mappedBy = "customer")
  private Set<AssignmentEntity> assignments = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private AddressEntity address;
}
