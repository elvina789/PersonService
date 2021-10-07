package com.elvina.PersonService.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@ToString
@EqualsAndHashCode
@Document
public class PersonDTO {
    @MongoId(value = FieldType.STRING)
    private String id;
    private String name;
    private Integer age;
    private GenderDTO gender;
    private Double height;
    private Double weight;
    private AddressDTO address;
}
