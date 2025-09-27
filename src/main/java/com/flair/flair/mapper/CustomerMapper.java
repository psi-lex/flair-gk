package com.flair.flair.mapper;

import com.flair.flair.model.CustomerTo;
import com.flair.flair.persistence.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "mobileNumber", source = "mobileNumber")
    @Mapping(target = "email", source = "email")
    CustomerTo mapCustomerEntityToCustomerTo(CustomerEntity source);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "mobileNumber", source = "mobileNumber")
    @Mapping(target = "email", source = "email")
    CustomerEntity mapCustomerToToCustomerEntity(CustomerTo source);
}
