package com.singlestone.calllist.controllers;

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
}
