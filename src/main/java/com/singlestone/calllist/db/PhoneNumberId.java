package com.singlestone.calllist.db;

import java.io.Serializable;

public class PhoneNumberId implements Serializable {
    private String number;
    private PhoneType type;

    public PhoneNumberId(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }
}
