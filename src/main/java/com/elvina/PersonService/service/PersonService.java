package com.elvina.PersonService.service;

import com.elvina.PersonService.exceptions.PersonAlreadyExistsException;
import com.elvina.PersonService.exceptions.PersonNotFoundException;
import com.elvina.PersonService.model.input.Person;

import java.util.List;

public interface PersonService {
    Person createPerson(Person person) throws PersonAlreadyExistsException;

    Person updatePerson(Person person) throws PersonNotFoundException;

    Person readPerson(String id) throws PersonNotFoundException;

    List<Person> readMultiplePerson(List<String> ids);

    Person deletePerson(String id) throws PersonNotFoundException;
}
