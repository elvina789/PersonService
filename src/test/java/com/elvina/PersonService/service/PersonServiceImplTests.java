package com.elvina.PersonService.service;

import com.elvina.PersonService.exceptions.PersonNotFoundException;
import com.elvina.PersonService.model.dto.PersonDTO;
import com.elvina.PersonService.model.input.Person;
import com.elvina.PersonService.persistence.PersonRepository;
import com.elvina.PersonService.utils.DtoMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonServiceImplTests {
    private static PersonRepository repositoryMock;
    private static DtoMapper dtoMapperMock;

    @BeforeAll
    public static void init() {
        repositoryMock = mock(PersonRepository.class);
        dtoMapperMock = mock(DtoMapper.class);
        when(dtoMapperMock.map(any(PersonDTO.class))).thenReturn(new Person());
        when(dtoMapperMock.map(any(Person.class))).thenReturn(new PersonDTO());
    }

    @Test
    public void personFoundTest() {
        when(repositoryMock.findById(anyString())).thenReturn(Optional.of(new PersonDTO()));
        PersonService personService = new PersonServiceImpl(repositoryMock, dtoMapperMock);
        Person person = null;
        try {
            person = personService.readPerson("123");
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        Assert.notNull(person, "Expected person to be found");
    }

    @Test
    public void personNotFoundTest() {
        when(repositoryMock.findById(anyString())).thenReturn(Optional.empty());
        PersonService personService = new PersonServiceImpl(repositoryMock, dtoMapperMock);
        Person person = null;
        try {
            person = personService.readPerson("123");
        } catch (PersonNotFoundException e) {
        }
        Assert.isNull(person, "Expected not to find person");
    }

    @Test
    public void foundMultiplePersonsTest() {
        when(repositoryMock.findById(anyString())).thenReturn(Optional.of(new PersonDTO()));
        PersonService personService = new PersonServiceImpl(repositoryMock, dtoMapperMock);
        List<Person> result = personService.readMultiplePerson(Arrays.asList("1231234561", "1231234562", "1231234563"));
        Assert.notEmpty(result, "Result returned empty");
        Assert.isTrue(result.size() == 3, "Found less persons than expected");
    }
}
