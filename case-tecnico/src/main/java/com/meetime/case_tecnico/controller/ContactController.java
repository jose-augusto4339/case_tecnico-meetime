package com.meetime.case_tecnico.controller;

import com.meetime.case_tecnico.dto.ContactDTO;
import com.meetime.case_tecnico.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createContact(@RequestBody ContactDTO contactRequest) {
        return contactService.createContact(contactRequest);
    }

}
