package com.irojas.demojwt.Contact;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ContactController {
    @Autowired
    private ContactService cService;

    @GetMapping("/user/{username}/contacts")
    public ResponseEntity<List<Contact>> getAllContactsByUsername (@PathVariable String username){
        if (!cService.usernameExist(username)) {
            throw new ResolutionException("No user was found with id = " + username);
        }

        List<Contact> contacts = cService.findByUsername(username);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }     

    @PostMapping("/user/{username}/contacts")
    public ResponseEntity<?> createContact(@PathVariable String username, @RequestBody Contact contactinfo){
        Optional<Contact> contact = cService.createContact(username, contactinfo);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @PutMapping("/user/{username}/contacts/{id}")
    public ResponseEntity<?> updateContact(@PathVariable("id") long id, @RequestBody Contact contactinfo){
        Contact contact = cService.updateContact(id, contactinfo);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.ACCEPTED);    
        }
        return ResponseEntity.notFound().build();
    }
}
