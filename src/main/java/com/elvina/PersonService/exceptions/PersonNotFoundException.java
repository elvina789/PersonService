package com.elvina.PersonService.exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException() {
        super("Person was not found");
    }
}
