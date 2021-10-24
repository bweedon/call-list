package com.singlestone.calllist.db;

import javax.persistence.*;

@Entity
public class PhoneNumber {
    @Id
    private int Id;
    private String number;
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


