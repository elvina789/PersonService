package com.elvina.PersonService.model.input;

import com.elvina.PersonService.validations.StateSubset;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@ToString
@EqualsAndHashCode
public class Address {
    @NotNull
    @StateSubset(anyOf = {State.Israel})
    private State state;
    @NotNull
    @Size(min = 3, max = 20)
    private String city;
    @NotNull
    @Size(min = 3, max = 50)
    private String street;
    @NotNull
    @Pattern(regexp = "^[1-9]\\d*$")
    private String zipCode;

    /*
    I chose to store containsAnimals as a String and not Boolean
    because the transformation of number to Boolean happens in the seriallization stage (before Spring validations)
     */
    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String containsAnimals;
}
