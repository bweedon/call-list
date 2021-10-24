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
}

enum PhoneType {
    MOBILE,
    WORK,
    HOME
}


