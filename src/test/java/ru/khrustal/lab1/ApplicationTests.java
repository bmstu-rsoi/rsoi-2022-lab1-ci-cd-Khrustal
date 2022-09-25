package ru.khrustal.lab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.khrustal.lab1.model.Person;
import ru.khrustal.lab1.repository.PersonRepository;
import ru.khrustal.lab1.service.PersonService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonRepository personRepository;

	private static final Person testPerson =
			Person.builder()
					.name("Alex")
					.age(18)
					.address("Moscow")
					.work("Junior Java Developer")
					.build();

	@Test
	void saveTest() {
		ResponseEntity<?> response = personService.createPerson(testPerson);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void updateTest() {
		Person person = personRepository.save(testPerson);
		person.setName("Vladimir");
		ResponseEntity<?> response = personService.updatePerson(person.getId(), person);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void getTest() {
		Person person = personRepository.save(testPerson);
		ResponseEntity<?> response = personService.getPerson(person.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody() instanceof Person);
	}

	@Test
	void getAllTest() {
		personRepository.save(testPerson);
		ResponseEntity<?> response = personService.getAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody() instanceof List);
	}

	@Test
	void deleteTest() {
		Person person = personRepository.save(testPerson);
		ResponseEntity<?> response = personService.deletePerson(person.getId());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
