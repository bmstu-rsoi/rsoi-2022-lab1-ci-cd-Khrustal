package ru.khrustal.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khrustal.lab1.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
