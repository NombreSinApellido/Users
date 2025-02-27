package com.irojas.demojwt.Contact;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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

    public List<Contact> findByUsername(String username) {
        return cRepository.findByUsername(username);
    }

    public void createContact(String username, Contact contactinfo) {
        Contact contact = uRepository.findByUsername(username)
    
        /* Optional<Object> contact = uRepository.findByUsername(username).map(user ->{
            contactinfo.setUserEmail(user);
        return cRepository.save(contactinfo);
        }).orElseThrow(()-> new Exception("no se encuentra")); */
    }

}
