package com.flair.flair.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String city;
    private String postalCode;
    private String street;
    private String houseNumber;

}
