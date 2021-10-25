package com.singlestone.calllist.controllers;

import com.singlestone.calllist.dto.ContactDto;
import com.singlestone.calllist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("")
    public List<ContactDto> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping("")
    public ContactDto addContact() {
        return null;
    }
}
