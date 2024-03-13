package com.example.demo001.controller;

import com.example.demo001.dto.AddressDto;
import com.example.demo001.dto.PersonDto;
import com.example.demo001.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public PersonDto createPerson(@RequestBody PersonDto personDto) {
        return personService.savePerson(personDto);
    }

    @PostMapping("/persons")
    public List<PersonDto> createPersons(@RequestBody List<PersonDto> personDtoList) {
        return personService.savePersons(personDtoList);
    }

    @PostMapping("/address")
    public List<AddressDto> createAddress(@RequestBody List<AddressDto> addressDtoList) {
        return personService.saveAddress(addressDtoList);
    }

    @GetMapping("/persons/{id}")
    public PersonDto findPersonById(@PathVariable int id) {
        return personService.getPersonById(id);
    }

//    @GetMapping("/persons/{firstName}")
//    public PersonDto findPersonByFirstName(@PathVariable String firstName) {
//        return personService.getPersonByFirstName(firstName);
//    }
//
//    @GetMapping("/persons/{lastName}")
//    public PersonDto findPersonByLastName(@PathVariable String lastName) {
//        return personService.getPersonByLastName(lastName);
//    }

    @GetMapping("/persons")
    public List<PersonDto> findAllPersons() {
        return personService.getPersons();
    }

    @PutMapping("/persons/{id}")
    public PersonDto updatePerson(@RequestBody PersonDto personDto, @PathVariable int id) {
        return personService.updatePerson(personDto, id);
    }

    @PutMapping("/address/{id}")
    public AddressDto updateAddress(@RequestBody AddressDto addressDto, @PathVariable int id) {
        return personService.updateAddress(addressDto, id);
    }

    @DeleteMapping("/persons/{id}")
    public String deletePerson(@PathVariable int id) {
        return personService.deletePerson(id);
    }

    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable int id) {
        return personService.deleteAddress(id);
    }

    @DeleteMapping("/persons")
    public String clearPersons() {
        return personService.clearPersons();
    }
}

