package com.flair.flair.validator;

import com.flair.flair.exception.validation.address.CityValidationException;
import com.flair.flair.exception.validation.address.HouseNumberValidationException;
import com.flair.flair.exception.validation.address.PostalCodeValidationException;
import com.flair.flair.exception.validation.address.StreetValidationException;
import com.flair.flair.model.AddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator {

  private static final String CITY_REGEX = "^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż \\-]{2,50}$";
  private static final String STREET_REGEX = "^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż  \\-]{2,50}$";
  private static final String POSTAL_CODE_REGEX = "^\\d{2}-\\d{3}$";
  private static final String HOUSE_NUMBER_REGEX = "^\\d+[a-zA-Z]?(/\\d+)?$";

  public void validateAddress(AddressRequest address) {

    if (!CITY_REGEX.matches(address.getCity())) {
      throw new CityValidationException("City has wrong format!");
    }
    if (!STREET_REGEX.matches(address.getStreet())) {
      throw new StreetValidationException("Street has wrong format!");
    }
    if (!POSTAL_CODE_REGEX.matches(address.getPostalCode())) {
      throw new PostalCodeValidationException("Postal code has wrong format!");
    }
    if (!HOUSE_NUMBER_REGEX.matches(address.getHouseNumber())) {
      throw new HouseNumberValidationException("House number has wrong format!");
    }
  }
}
