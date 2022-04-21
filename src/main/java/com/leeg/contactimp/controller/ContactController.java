package com.leeg.contactimp.controller;

import com.leeg.contactimp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ContactController {

    private ContactService contactService;

    @GetMapping(value = "/contacts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getContactsByUser(@RequestHeader("token") String token) {
        try {
            return new ResponseEntity<>(contactService.getContactsByUser(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
