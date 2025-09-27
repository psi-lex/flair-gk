package com.flair.flair.model;

import com.flair.flair.enums.AssignmentType;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewAssignmentRequest {

  private Set<Long> employeesId;
  private AssignmentType assignmentType;
  private AddressRequest address;
  private CustomerTo customerTo;
}
