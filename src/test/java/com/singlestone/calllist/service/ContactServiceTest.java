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
        ContactDto toAdd = getTestContact();
        contactService.addContact(toAdd);
        List<ContactDto> added = contactService.getAllContacts();
        added.forEach(c -> {
            //Test the phone list is created correctly, I hadn't realized this wasn't working earlier.
            assertEquals(1, c.getPhone().size());
            assertEquals(toAdd.getName().getLast(), c.getName().getLast());
        });
    }

    @Test
    void getById_NoneFound() {
        ContactDto shouldBeNull = contactService.getContactById(42);
        assertNull(shouldBeNull);
    }

    @Test
    void getById_Found() {
        ContactDto toAdd = getTestContact();
        ContactDto added = contactService.addContact(toAdd);
        ContactDto retrieved = contactService.getContactById(added.getId());
        assertNotNull(retrieved);
        assertEquals(added.getId(), retrieved.getId());
        assertEquals(added.getPhone().size(), retrieved.getPhone().size());
    }

    @Test
    void updateById_Found() {
        ContactDto toAdd = getTestContact();
        ContactDto added = contactService.addContact(toAdd);
        final String updatedString = "Updated";
        added.getName().setFirst(updatedString);
        added.getAddress().setStreet(updatedString);
        ContactDto updated = contactService.updateContact(added);
        ContactDto updatedRetrieved = contactService.getContactById(updated.getId());
        // TODO: Not checking phone number updates here because I ran into some weirdness because
        // TODO: the tests are using the same object. Maybe come back and script the test db entries instead.
        assertEquals(updatedString, updatedRetrieved.getName().getFirst());
        assertEquals(updatedString, updatedRetrieved.getAddress().getStreet());
    }

    @Test
    void updateById_NotFound() {
        ContactDto notExists = getTestContact();
        notExists.setId(42);
        ContactDto shouldBeNull = contactService.updateContact(notExists);
        assertNull(shouldBeNull);
    }

    private ContactDto getTestContact() {
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
        return toAdd;

    }
}