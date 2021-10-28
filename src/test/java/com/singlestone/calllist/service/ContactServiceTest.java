package com.singlestone.calllist.service;

import com.singlestone.calllist.db.dao.PersonDao;
import com.singlestone.calllist.db.model.PhoneType;
import com.singlestone.calllist.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Autowired
    PersonDao personDao;

    @BeforeEach
    void wipeDB() {
        personDao.deleteAll();
    }

    @Test
    @Order(1)
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
    @Order(2)
    void getById_NoneFound() {
        ContactDto shouldBeNull = contactService.getContactById(42);
        assertNull(shouldBeNull);
    }

    @Test
    @Order(3)
    void getById_Found() {
        ContactDto toAdd = getTestContact();
        ContactDto added = contactService.addContact(toAdd);
        ContactDto retrieved = contactService.getContactById(added.getId());
        assertNotNull(retrieved);
        assertEquals(added.getId(), retrieved.getId());
        assertEquals(added.getPhone().size(), retrieved.getPhone().size());
    }

    @Test
    @Order(4)
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
    @Order(5)
    void updateById_NotFound() {
        ContactDto notExists = getTestContact();
        notExists.setId(42);
        ContactDto shouldBeNull = contactService.updateContact(notExists);
        assertNull(shouldBeNull);
    }

    @Test
    @Order(6)
    void deleteById_Found() {
        ContactDto toAdd = getTestContact();
        ContactDto added = contactService.addContact(toAdd);
        // Make sure it was added
        ContactDto retrieved = contactService.getContactById(added.getId());
        assertEquals(added.getId(), retrieved.getId());
        contactService.deleteContactById(retrieved.getId());
        assertNull(contactService.getContactById(retrieved.getId()));
    }

    // This test is more just to make sure an exception doesn't happen
    @Test
    @Order(7)
    void deleteById_NotFound() {
        contactService.deleteContactById(42);
    }

    @Test
    @Order(8)
    void callList_Filter() {
        ContactDto toAdd1 = getTestContact();
        ContactDto toAdd2 = getTestContact();
        ContactDto toAdd3 = getTestContact();
        toAdd3.setPhone(Arrays.asList(
                new PhoneNumber("not home", PhoneType.WORK)
        ));
        contactService.addContact(toAdd1);
        contactService.addContact(toAdd2);
        contactService.addContact(toAdd3);
        List<CallEntryDto> callList = contactService.getCallList();
        Assertions.assertEquals(2, callList.size());
    }

    @Test
    @Order(9)
    void callList_TestOrder() {
        ContactDto toAdd1 = getTestContact();
        ContactDto toAdd2 = getTestContact();
        ContactDto toAdd3 = getTestContact();
        ContactDto toAdd4 = getTestContact();
        ContactDto toAdd5 = getTestContact();
        toAdd1.setPhone(Arrays.asList(
                new PhoneNumber("not home", PhoneType.MOBILE)
        ));
        toAdd2.getName().setFirst("a");
        toAdd2.getName().setLast("a");
        toAdd3.getName().setFirst("a");
        toAdd3.getName().setLast("c");
        toAdd4.getName().setFirst("c");
        toAdd4.getName().setLast("a");
        toAdd5.getName().setFirst("b");
        toAdd5.getName().setLast("a");
        contactService.addContact(toAdd1);
        contactService.addContact(toAdd2);
        contactService.addContact(toAdd3);
        contactService.addContact(toAdd4);
        contactService.addContact(toAdd5);
        List<CallEntryDto> callList = contactService.getCallList();
        Assertions.assertEquals(4, callList.size());
        Assertions.assertEquals("a", callList.get(0).getName().getFirst());
        Assertions.assertEquals("a", callList.get(0).getName().getLast());
        Assertions.assertEquals("b", callList.get(1).getName().getFirst());
        Assertions.assertEquals("a", callList.get(1).getName().getLast());
        Assertions.assertEquals("c", callList.get(2).getName().getFirst());
        Assertions.assertEquals("a", callList.get(2).getName().getLast());
        Assertions.assertEquals("a", callList.get(3).getName().getFirst());
        Assertions.assertEquals("c", callList.get(3).getName().getLast());
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