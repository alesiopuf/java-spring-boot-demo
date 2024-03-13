package com.example.demo001.service;

import com.example.demo001.converter.AddressConverter;
import com.example.demo001.converter.PersonConverter;
import com.example.demo001.dto.AddressDto;
import com.example.demo001.dto.PersonDto;
import com.example.demo001.entity.Address;
import com.example.demo001.entity.Person;
import com.example.demo001.repository.AddressRepository;
import com.example.demo001.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonConverter personConverter;
    @Autowired
    private AddressConverter addressConverter;

    public PersonDto savePerson(PersonDto personDto) {
        Person person = personConverter.toEntity(personDto);
        if (personDto.getAddressIdList() != null) {
            for(int addressId : personDto.getAddressIdList()) {
                Address address = addressRepository.findById(addressId).orElse(null);
                person.addAddress(address);
            }
        }

        return personConverter.toDto(personRepository.save(person));
    }

    public List<PersonDto> savePersons(List<PersonDto> personDtoList) {
        List<Person> personList = new ArrayList<Person>();
        for (PersonDto personDto : personDtoList) {
            Person person = personConverter.toEntity(personDto);
            for(int addressId : personDto.getAddressIdList()) {
                Address address = addressRepository.findById(addressId).orElse(null);
                person.addAddress(address);
            }
            personList.add(person);
        }
        return personConverter.toDtoList(personRepository.saveAll(personList));
    }

    public List<AddressDto> saveAddress(List<AddressDto> addressDtoList) {
        return addressConverter.toDtoList(addressRepository.saveAll(addressConverter.toEntityList(addressDtoList)));
    }

    public PersonDto getPersonById(int id) {
        return personConverter.toDto(Objects.requireNonNull(personRepository.findById(id).orElse(null)));
    }

    public PersonDto getPersonByFirstName(String firstName) {
        return personConverter.toDto(personRepository.findPersonByFirstName(firstName));
    }

    public PersonDto getPersonByLastName(String lastName) {
        return personConverter.toDto(personRepository.findPersonByLastName(lastName));
    }

    public List<PersonDto> getPersons() {
        return personConverter.toDtoList(personRepository.findAll());
    }

    public PersonDto updatePerson(PersonDto personDto, int id) {
        Person newPerson = personRepository.findById(id).orElse(null);
        //delete old addresses
        assert newPerson != null;
        for(Address address : newPerson.getAddressList()) {
            address.setPerson(null);
        }
        newPerson.clearAddresses();
        //add new addresses
        for(int addressId : personDto.getAddressIdList()) {
            Address address = addressRepository.findById(addressId).orElse(null);
            newPerson.addAddress(address);
        }
        newPerson.setFirstName(personDto.getFirstName());
        newPerson.setLastName(personDto.getLastName());
        return personConverter.toDto(personRepository.save(newPerson));
    }

    public AddressDto updateAddress(AddressDto addressDto, int id) {
        Address address = addressRepository.findById(id).orElse(null);
        assert address != null;
        address.setNumber(addressDto.getNumber());
        address.setStreet(addressDto.getStreet());
        return addressConverter.toDto(addressRepository.save(address));
    }

    public String deletePerson(int id) {
        Person person = personRepository.findById(id).orElse(null);
        assert person != null;
        for(Address address : person.getAddressList()) {
            address.setPerson(null);
        }
        person.clearAddresses();
        personRepository.deleteById(id);
        return "Person " + id + " removed.";
    }

    public String deleteAddress(int id) {
        Address address = addressRepository.findById(id).orElse(null);
        assert address != null;
        Person person = address.getPerson();
        person.removeAddress(address);
        addressRepository.deleteById(id);
        return "Address " + id + " removed.";
    }

    public String clearPersons() {
        addressRepository.deleteAll();
        personRepository.deleteAll();
        return "Tables cleared.";
    }
}