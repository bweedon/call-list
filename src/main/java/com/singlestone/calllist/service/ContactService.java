package com.singlestone.calllist.service;

import com.singlestone.calllist.db.dao.PersonDao;
import com.singlestone.calllist.db.model.Person;
import com.singlestone.calllist.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    PersonDao personDao;

    public List<ContactDto> getAllContacts() {
        List<Person> persons = personDao.findAll();
        // First time using streams, lots of help from stackoverflow on this one.
        // But this simplifies a for loop where I would loop through all the person objects
        // and convert it to a contact object, then add that contact object to the contacts list.
        List<ContactDto> contacts = persons.stream().map(ContactDto::From).collect(Collectors.toList());
        return contacts;
    }

    public ContactDto addContact(ContactDto toAdd) {
        Person personToAdd = Person.From(toAdd);
        // Set the person object on the phone number so that they are persisted correctly.
        personToAdd.getPhoneNumbers().forEach(phoneNumber -> {
            phoneNumber.setPerson(personToAdd);
        });
        Person added = personDao.save(personToAdd);
        return ContactDto.From(added);
    }

    public ContactDto getContactById(int contactId) {
        Person fetched = personDao.findById(contactId).orElse(null);
        ContactDto fetchedContact = new ContactDto();
        if(fetched != null) {
            return ContactDto.From(fetched);
        } else {
            return null;
        }
    }
}
