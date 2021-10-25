package com.singlestone.calllist.service;

import com.singlestone.calllist.db.model.PhoneType;
import com.singlestone.calllist.dto.Address;
import com.singlestone.calllist.dto.ContactDto;
import com.singlestone.calllist.dto.Name;
import com.singlestone.calllist.dto.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Test
    void addAndRetrieveContact() {
        ContactDto toAdd = new ContactDto();
        toAdd.setName(new Name(
                "first",
                "middle",
                "last"
        ));
        toAdd.setAddress(new Address(
                "street",
                "city",
                "state",
                123
        ));
        toAdd.setPhone(Arrays.asList(
                new PhoneNumber("phonenumber", PhoneType.HOME)
        ));
        toAdd.setEmail("email@example.com");
        contactService.addContact(toAdd);
        List<ContactDto> added = contactService.getAllContacts();
        added.forEach(c -> {
            //Test the phone list is created correctly, I hadn't realized this wasn't working earlier.
            assertEquals(1, c.getPhone().size());
            assertEquals(toAdd.getName().getLast(), c.getName().getLast());
        });
    }
}