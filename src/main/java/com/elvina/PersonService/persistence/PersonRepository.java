package com.elvina.PersonService.persistence;

import com.elvina.PersonService.model.dto.PersonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonDTO, String> {
}
