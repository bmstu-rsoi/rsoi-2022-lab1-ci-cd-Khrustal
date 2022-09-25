package ru.khrustal.lab1.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.khrustal.lab1.model.Person;
import ru.khrustal.lab1.service.PersonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{personId}")
    public ResponseEntity<?> getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPersons() {
        return personService.getAll();
    }

    @PostMapping("")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<?> getPerson(@PathVariable Long personId, @RequestBody Person person) {
        return personService.updatePerson(personId, person);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        return personService.deletePerson(personId);
    }
}
