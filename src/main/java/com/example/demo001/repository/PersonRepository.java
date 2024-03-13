package com.example.demo001.repository;

import com.example.demo001.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findPersonByFirstName(String firstName);
    Person findPersonByLastName(String lastName);
}
