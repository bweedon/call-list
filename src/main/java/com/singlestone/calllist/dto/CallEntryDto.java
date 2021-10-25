package com.singlestone.calllist.dto;

import com.singlestone.calllist.db.model.Person;
import com.singlestone.calllist.db.model.PhoneNumber;
import com.singlestone.calllist.db.model.PhoneType;

public class CallEntryDto {
    private Name name;
    private String phone;
    public CallEntryDto() { }
    public CallEntryDto(Name name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Assume at this point that only entries with home phones
    public static CallEntryDto From(Person person) {
        // Filter down the phone number list to find the first phone with type home otherwise return null.
        PhoneNumber home = person.getPhoneNumbers().stream()
                .filter(p -> p.getType() == PhoneType.HOME).findFirst().orElse(null);
        Name name = new Name(person.getFirstName(), person.getMiddleName(), person.getLastName());
        return new CallEntryDto(name, home.getNumber());
    }
}
