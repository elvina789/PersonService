package com.elvina.PersonService.controller;

import com.elvina.PersonService.exceptions.PersonAlreadyExistsException;
import com.elvina.PersonService.exceptions.PersonNotFoundException;
import com.elvina.PersonService.model.input.Person;
import com.elvina.PersonService.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody Person person) throws PersonAlreadyExistsException {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePerson(@Valid @RequestBody Person person) throws PersonNotFoundException {
        return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> readPerson(@PathVariable String id) throws PersonNotFoundException {
        return new ResponseEntity<>(personService.readPerson(id), HttpStatus.OK);
    }

    @GetMapping(params = "ids")
    public ResponseEntity<?> readMultiplePersons(@RequestParam List<String> ids) {
        return new ResponseEntity<>(personService.readMultiplePerson(ids), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePerson(@PathVariable String id) throws PersonNotFoundException {
        return new ResponseEntity<>(personService.deletePerson(id), HttpStatus.OK);
    }
}
