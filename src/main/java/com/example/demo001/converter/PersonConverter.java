package com.example.demo001.converter;

import com.example.demo001.dto.PersonDto;
import com.example.demo001.entity.Address;
import com.example.demo001.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonConverter {

    @Autowired
    AddressConverter addressConverter;

    public Person toEntity(PersonDto personDto) {
        Person person = new Person();
        //person.setAddressList(addressConverter.toEntityList(personDto.getAddressDtoList()));
        person.setAddressList(new ArrayList<Address>());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        return person;
    }

    public List<Person> toEntityList(List<PersonDto> personDtoList) {
        List<Person> personList = new ArrayList<>();
        for (PersonDto personDto : personDtoList) {
            Person person = new Person();
            //person.setAddressList(addressConverter.toEntityList(personDto.getAddressDtoList()));
            person.setAddressList(new ArrayList<Address>());
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            personList.add(person);
        }
        return personList;
    }

    public PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setAddressIdList(addressConverter.toIdList(person.getAddressList()));
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        return personDto;
    }

    public List<PersonDto> toDtoList(List<Person> personList) {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : personList) {
            PersonDto personDto = new PersonDto();
            personDto.setAddressIdList(addressConverter.toIdList(person.getAddressList()));
            personDto.setFirstName(person.getFirstName());
            personDto.setLastName(person.getLastName());
            personDtoList.add(personDto);
        }
        return personDtoList;
    }
}
