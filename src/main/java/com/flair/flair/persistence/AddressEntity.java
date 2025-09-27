package com.flair.flair.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "house_number", nullable = false)
  private String houseNumber;

  @OneToOne(mappedBy = "address")
  private EmployeeEntity employee;

  @OneToOne(mappedBy = "address")
  private AssignmentEntity assignment;

  @OneToOne(mappedBy = "address")
  private CustomerEntity customer;
}
