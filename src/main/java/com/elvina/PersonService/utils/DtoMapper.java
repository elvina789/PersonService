package com.elvina.PersonService.utils;

import com.elvina.PersonService.model.dto.PersonDTO;
import com.elvina.PersonService.model.input.Person;

public interface DtoMapper {
    PersonDTO map(Person person);

    Person map(PersonDTO personDto);
}
