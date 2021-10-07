package com.elvina.PersonService.utils;

import com.elvina.PersonService.model.dto.PersonDTO;
import com.elvina.PersonService.model.input.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapperImpl implements DtoMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public PersonDTO map(Person person) {
        return mapper.convertValue(person, PersonDTO.class);
    }

    @Override
    public Person map(PersonDTO personDto) {
        return mapper.convertValue(personDto, Person.class);
    }
}
