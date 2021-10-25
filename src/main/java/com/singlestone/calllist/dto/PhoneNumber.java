package com.singlestone.calllist.dto;

import com.singlestone.calllist.db.model.PhoneType;

public class PhoneNumber {
    private String number;
    private PhoneType type;

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

    public PhoneNumber() { }
    public PhoneNumber(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }
    public static PhoneNumber From(com.singlestone.calllist.db.model.PhoneNumber phone) {
        return new PhoneNumber(phone.getNumber(), phone.getType());
    }
}
