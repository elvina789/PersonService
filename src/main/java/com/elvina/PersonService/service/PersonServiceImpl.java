package com.elvina.PersonService.service;

import com.elvina.PersonService.exceptions.PersonAlreadyExistsException;
import com.elvina.PersonService.exceptions.PersonNotFoundException;
import com.elvina.PersonService.exceptions.PersonServiceException;
import com.elvina.PersonService.model.dto.PersonDTO;
import com.elvina.PersonService.model.input.Person;
import com.elvina.PersonService.persistence.PersonRepository;
import com.elvina.PersonService.utils.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final DtoMapper dtoMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, DtoMapper dtoMapper) {
        this.personRepository = personRepository;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public Person createPerson(Person person) throws PersonAlreadyExistsException {
        log.info("creating person");
        try {
            if (personRepository.existsById(person.getId())) {
                throw new PersonAlreadyExistsException();
            } else {
                PersonDTO personDto = personRepository.insert(dtoMapper.map(person));
                log.info("Person was created");
                return dtoMapper.map(personDto);
            }
        } catch (RuntimeException e) {
            log.error("Failed to create person", e);
            throw new PersonServiceException(e);
        }
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundException {
        log.info("updating person");
        try {
            if (personRepository.existsById(person.getId())) {
                log.info("Person exists performing update");
                PersonDTO personDTO = personRepository.save(dtoMapper.map(person));
                log.info("Person was updated");
                return dtoMapper.map(personDTO);
            } else {
                log.error("Person was not found");
                throw new PersonNotFoundException();
            }
        } catch (RuntimeException e) {
            log.error("Failed to update person", e);
            throw new PersonServiceException(e);
        }
    }

    @Override
    public Person readPerson(String id) throws PersonNotFoundException {
        log.info("reading person by id {}", id);
        try {
            Optional<PersonDTO> optional = personRepository.findById(id);
            if (optional.isPresent()) {
                log.info("Person with id {} exists performing read", id);
                PersonDTO personDto = optional.get();
                return dtoMapper.map(personDto);
            } else {
                log.error("Person was not found");
                throw new PersonNotFoundException();
            }
        } catch (RuntimeException e) {
            log.error("Failed to read person", e);
            throw new PersonServiceException(e);
        }
    }

    @Override
    public List<Person> readMultiplePerson(List<String> ids) {
        log.info("reading multiple persons by id");
        List<Person> result = new ArrayList<>();
        for (String id : ids) {
            try {
                Person person = readPerson(id);
                result.add(person);
            } catch (PersonNotFoundException e) {
                log.error("Failed to read person {}", id);
            }
        }
        return result;
    }

    @Override
    public Person deletePerson(String id) throws PersonNotFoundException {
        log.info("deleting person by id {}", id);
        try {
            Optional<PersonDTO> optional = personRepository.findById(id);
            if (optional.isPresent()) {
                log.info("Person with id {} exists performing delete", id);
                PersonDTO personDTO = optional.get();
                personRepository.deleteById(id);
                return dtoMapper.map(personDTO);
            } else {
                log.error("Person was not found");
                throw new PersonNotFoundException();
            }
        } catch (RuntimeException e) {
            log.error("Failed to delete person", e);
            throw new PersonServiceException(e);
        }
    }
}
