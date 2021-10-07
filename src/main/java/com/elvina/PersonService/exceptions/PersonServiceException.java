package com.elvina.PersonService.exceptions;

public class PersonServiceException extends RuntimeException {
    public PersonServiceException(Exception e) {
        super("Person service error happened", e);
    }
}
