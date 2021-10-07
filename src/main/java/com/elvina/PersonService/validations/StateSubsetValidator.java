package com.elvina.PersonService.validations;

import com.elvina.PersonService.model.input.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StateSubsetValidator implements ConstraintValidator<StateSubset, State> {
    private State[] subset;

    @Override
    public void initialize(StateSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(State value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}