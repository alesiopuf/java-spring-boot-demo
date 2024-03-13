package com.example.demo001.dto;

import java.util.List;

public class PersonDto {
    private String firstName;
    private String lastName;
    private List<Integer> addressIdList;

    public List<Integer> getAddressIdList() {
        return addressIdList;
    }

    public void setAddressIdList(List<Integer> addressIdList) {
        this.addressIdList = addressIdList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
