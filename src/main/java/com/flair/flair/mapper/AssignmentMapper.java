package com.flair.flair.mapper;

import com.flair.flair.model.NewAssignmentRequest;
import com.flair.flair.persistence.AssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CustomerMapper.class)
public interface AssignmentMapper {



    //NewAssignmentRequest mapAssignmentEntityToNewAssignmentRequest(AssignmentEntity source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "type", source = "assignmentType")
    @Mapping(target = "customer", source = "customerTo")
    AssignmentEntity mapNewAssignmentRequestToAssignmentEntity(NewAssignmentRequest source);

}
