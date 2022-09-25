package ru.khrustal.lab1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrustal.lab1.dto.ErrorResponse;
import ru.khrustal.lab1.dto.ValidationErrorResponse;
import ru.khrustal.lab1.model.Person;
import ru.khrustal.lab1.repository.PersonRepository;
import ru.khrustal.lab1.service.PersonService;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public ResponseEntity<?> getPerson(Long id) {
        if(id == null) return ResponseEntity.badRequest().build();
        Person result = personRepository.findById(id).orElse(null);
        if(result != null) return ResponseEntity.ok(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Record not found"));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Person> persons = personRepository.findAll();
        return ResponseEntity.ok(persons);
    }

    @Override
    @Transactional
    public ResponseEntity<?> createPerson(Person person) {
        if(person == null || person.isEmpty()) return ResponseEntity.badRequest().body(new ValidationErrorResponse());
        person.setId(null);
        Person savedPerson = personRepository.save(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/persons/" + savedPerson.getId())
                .build();
    }

    @Override
    public ResponseEntity<?> updatePerson(Long id, Person person) {
        if(person == null || id == null || person.isEmpty()) return ResponseEntity.badRequest().body(new ValidationErrorResponse());
        if(personRepository.existsById(id)) {
            person.setId(id);
            Person savedPerson = personRepository.save(person);
            return ResponseEntity.ok(savedPerson);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Record not found"));
    }

    @Override
    public ResponseEntity<?> deletePerson(Long id) {
        if(id == null) return ResponseEntity.badRequest().build();
        if(personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
