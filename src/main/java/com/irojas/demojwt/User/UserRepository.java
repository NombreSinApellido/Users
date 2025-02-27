package com.irojas.demojwt.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.irojas.demojwt.Contact.Contact;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByActiveTrueAndUsername(String username);

    @Query("SELECT c FROM Contact c WHERE c.userEmail.username = :username")
    List<Contact> getAllContactsByUsername(@Param("username") String username);

}
