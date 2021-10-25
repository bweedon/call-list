package com.singlestone.calllist.db.model;

import javax.persistence.*;

@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String number;
    @Enumerated(EnumType.STRING)
    private PhoneType type;
    @ManyToOne
    private Person person;

    public PhoneNumber() { }
    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static PhoneNumber From(com.singlestone.calllist.dto.PhoneNumber phone) {
        return new PhoneNumber(phone.getNumber(), phone.getType());
    }
}


