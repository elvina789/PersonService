package com.elvina.PersonService.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class AddressDTO {
    private StateDTO state;
    private String city;
    private String street;
    private String zipCode;
    private String containsAnimals;
}
