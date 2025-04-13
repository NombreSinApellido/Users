package com.irojas.demojwt.Contact;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.irojas.demojwt.User.User;
import com.irojas.demojwt.User.UserRepository;

@Service
public class ContactService {
    @Autowired
    private UserRepository uRepository;
    @Autowired
    private ContactRepository cRepository;

    public boolean usernameExist (String username){
        Optional<User> user = uRepository.findByUsername(username);
        if (user.isPresent()) {
            return true;            
        }
        return false;
    }

    public boolean contactExist (Long id){
        Optional<Contact> contact = cRepository.findById(id);
        if (contact.isPresent()) {
            return true;
        }
        return false;
    }

    public List<Contact> findByUsername(String userEmail) {
        return uRepository.getAllContactsByUsername(userEmail);
    }

    public Optional<Contact> createContact(String username, Contact contactinfo) {
    
        Optional<Contact> contact = uRepository.findByUsername(username).map(user ->{
            contactinfo.setUserEmail(user);
        return cRepository.save(contactinfo);
        });
                return contact;
    }

    public Contact updateContact(long id, Contact contactinfo) {
        Contact contact = cRepository.findById(id).orElse(null);
        /* .orElseThrow(() -> new ResolutionException("Contact Id " + id + "not found")); */
        if (contact != null) {
            contact.setFirstName(contactinfo.getFirstName());
            contact.setLastName(contactinfo.getLastName());
            contact.setAlias(contactinfo.getAlias());
            contact.setBankInfo(contactinfo.getBankInfo());
            return cRepository.save(contact); 
        }
        return null;
    }

    public void deleteContact(long id){
        if (cRepository.findById(id).isPresent()) {
            cRepository.deleteById(id);    
        }
    }
}
