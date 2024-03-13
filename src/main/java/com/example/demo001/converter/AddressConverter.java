package com.example.demo001.converter;

import com.example.demo001.dto.AddressDto;
import com.example.demo001.entity.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConverter {

    public Address toEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setNumber(addressDto.getNumber());
        address.setStreet(addressDto.getStreet());
        return address;
    }

    public List<Address> toEntityList(List<AddressDto> addressDtoList) {
        List<Address> addressList = new ArrayList<>();
        for (AddressDto addressDto : addressDtoList) {
            Address address = new Address();
            address.setNumber(addressDto.getNumber());
            address.setStreet(addressDto.getStreet());
            addressList.add(address);
        }
        return addressList;
    }

    public AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setNumber(address.getNumber());
        addressDto.setStreet(address.getStreet());
        return addressDto;
    }

    public List<AddressDto> toDtoList(List<Address> addressList) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (Address address : addressList) {
            AddressDto addressDto = new AddressDto();
            addressDto.setNumber(address.getNumber());
            addressDto.setStreet(address.getStreet());
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    public List<Integer> toIdList(List<Address> addressList) {
        List<Integer> addressIdList = new ArrayList<>();
        for (Address address : addressList) {
            addressIdList.add(address.getId());
        }
        return addressIdList;
    }
}
