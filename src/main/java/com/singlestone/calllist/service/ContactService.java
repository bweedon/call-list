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
        Person added = personDao.save(getPersonFromContact(toAdd));
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

    public ContactDto updateContact(ContactDto toUpdate) {
        Person personToUpdate = getPersonFromContact(toUpdate);
        // Not sure if this check is necessary based on the requirements or not, but went ahead and put it in.
        if(personDao.existsById(personToUpdate.getId())) {
            Person updated = personDao.save(getPersonFromContact(toUpdate));
            return ContactDto.From(updated);
        } else {
            //Don't update
            return null;
        }
    }

    public void deleteContactById(int contactId) {
        if(personDao.existsById(contactId)) {
            personDao.deleteById(contactId);
        }
    }

    private Person getPersonFromContact(ContactDto contact) {
        Person person = Person.From(contact);
        // Set the id here since it may be used in an update
        person.setId(contact.getId());
        // Set the person object on the phone number so that they are persisted correctly.
        person.getPhoneNumbers().forEach(phoneNumber -> {
            phoneNumber.setPerson(person);
        });
        return person;
    }
}
