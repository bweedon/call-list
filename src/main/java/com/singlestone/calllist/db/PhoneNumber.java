package com.singlestone.calllist.db;

import javax.persistence.*;

@Entity
@IdClass(PhoneNumberId.class)
public class PhoneNumber {
    @Id
    private String number;
    @Id
    private PhoneType type;
    @ManyToOne
    private Person person;

    public PhoneNumber() { }
    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }
}

enum PhoneType {
    MOBILE,
    WORK,
    HOME
}


