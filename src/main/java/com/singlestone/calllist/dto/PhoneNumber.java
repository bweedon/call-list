package com.singlestone.calllist.dto;

import com.singlestone.calllist.db.model.PhoneType;

public class PhoneNumber {
    public String number;
    public PhoneType type;

    public PhoneNumber() { }
    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }
    public static PhoneNumber From(com.singlestone.calllist.db.model.PhoneNumber phone) {
        return new PhoneNumber(phone.getNumber(), phone.getType());
    }
}
