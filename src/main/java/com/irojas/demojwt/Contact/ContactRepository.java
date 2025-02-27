package com.irojas.demojwt.Contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByUsername(String username);

}
