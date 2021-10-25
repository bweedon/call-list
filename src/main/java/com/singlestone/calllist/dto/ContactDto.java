package com.singlestone.calllist.dto;

import com.singlestone.calllist.db.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class ContactDto {
    private Name name;
    private Address address;
    private List<PhoneNumber> phone;
    private String email;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneNumber> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneNumber> phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static ContactDto From(Person person) {
        ContactDto contact = new ContactDto();
        contact.setName(new Name(person.getFirstName(), person.getMiddleName(), person.getLastName()));
        contact.setAddress(new Address(person.getStreet(), person.getCity(), person.getState(), person.getZip()));
        // Simplifies looping over all the db phone numbers and converting them to dto phone numbers.
        contact.setPhone(person.getPhoneNumbers().stream().map(PhoneNumber::From).collect(Collectors.toList()));
        contact.setEmail(person.getEmail());
        return contact;
    }

}

