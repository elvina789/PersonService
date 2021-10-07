package com.elvina.PersonService.model.input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
@EqualsAndHashCode
public class Person {
    @NotNull
    @Size(min = 3, max =  40)
    private String id;
    @NotNull
    @Size(min = 3, max =  20)
    private String name;
    @NotNull
    @Min(0)
    @Max(200)
    private Integer age;
    @NotNull
    private Gender gender;
    @NotNull
    @Min(0)
    private Double height;
    @Min(0)
    private Double weight;
    @NotNull
    @Valid
    private Address address;
}
