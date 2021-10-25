package com.singlestone.calllist.controllers;

import com.singlestone.calllist.dto.CallEntryDto;
import com.singlestone.calllist.dto.ContactDto;
import com.singlestone.calllist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping(value = "", produces = "application/json")
    public List<ContactDto> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ContactDto addContact(@RequestBody ContactDto toAdd) {
        return contactService.addContact(toAdd);
    }

    @GetMapping(value = "/{contactId}", produces = "application/json")
    public ContactDto getContactById(@PathVariable int contactId) {
        return contactService.getContactById(contactId);
    }

    @PutMapping(value = "/{contactId}", consumes = "application/json", produces = "application/json")
    public ContactDto updateContact(@RequestBody ContactDto toUpdate, @PathVariable int contactId) {
        //Set the id since in the spec that wouldn't be sent to me
        toUpdate.setId(contactId);
        return contactService.updateContact(toUpdate);
    }

    @DeleteMapping(value = "/{contactId}", produces = "application/json")
    public void deleteContact(@PathVariable int contactId) {
        contactService.deleteContactById(contactId);
    }

    @GetMapping(value = "/call-list", produces = "application/json")
    public List<CallEntryDto> getCallList() {
        return contactService.getCallList();
    }
}
