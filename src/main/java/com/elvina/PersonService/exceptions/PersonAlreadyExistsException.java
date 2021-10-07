package com.elvina.PersonService.exceptions;

public class PersonAlreadyExistsException extends Exception {
    public PersonAlreadyExistsException() {
        super("Person already exists");
    }
}