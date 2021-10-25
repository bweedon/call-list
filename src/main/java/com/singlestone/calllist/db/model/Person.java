package com.singlestone.calllist.db.model;

import com.singlestone.calllist.dto.ContactDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String email;
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public Person() { }

    public Person(String firstName, String middleName, String lastName, String street, String city, String state, int zip, String email, List<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public static Person From(ContactDto contact) {
        return new Person(
                contact.getName().getFirst(),
                contact.getName().getMiddle(),
                contact.getName().getLast(),
                contact.getAddress().getStreet(),
                contact.getAddress().getCity(),
                contact.getAddress().getState(),
                contact.getAddress().getZip(),
                contact.getEmail(),
                // Simplifies looping and converting from one PhoneNumber to another.
                contact.getPhone().stream().map(PhoneNumber::From).collect(Collectors.toList())
        );
    }
}
