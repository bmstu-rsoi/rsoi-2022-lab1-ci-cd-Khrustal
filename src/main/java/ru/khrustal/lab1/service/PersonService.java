package ru.khrustal.lab1.service;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import ru.khrustal.lab1.model.Person;

public interface PersonService {
    ResponseEntity<?> getPerson(Long id);
    ResponseEntity<?> getAll();
    ResponseEntity<?> createPerson(Person person);
    ResponseEntity<?> updatePerson(Long id, Person person);
    ResponseEntity<?> deletePerson(Long id);
}
