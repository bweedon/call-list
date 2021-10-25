package com.singlestone.calllist.service;

import com.singlestone.calllist.db.dao.PersonDao;
import com.singlestone.calllist.db.model.Person;
import com.singlestone.calllist.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    PersonDao personDao;

    public List<ContactDto> getAllContacts() {
        List<ContactDto> contacts = new ArrayList<>();
        List<Person> persons = personDao.findAll();
        // First time using streams, lots of help from stackoverflow on this one.
        // But this simplifies a for loop where I would loop through all the person objects
        // and convert it to a contact object, then add it to the contacts list.
        contacts = persons.stream().map(ContactDto::From).collect(Collectors.toList());
        return contacts;
    }

}
