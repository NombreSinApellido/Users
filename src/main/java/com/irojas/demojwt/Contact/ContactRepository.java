package com.irojas.demojwt.Contact;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {

    /* @Query("SELECT c FROM Contact c WHERE c.user.username = :username")
    List<Contact> getAllContactsByUsername(@Param("username") String userEmail); */

}
