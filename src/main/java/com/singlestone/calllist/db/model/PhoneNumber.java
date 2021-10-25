package com.singlestone.calllist.db.model;

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

    public static PhoneNumber From(com.singlestone.calllist.dto.PhoneNumber phone) {
        return new PhoneNumber(phone.getNumber(), phone.getType());
    }
}


