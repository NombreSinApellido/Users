package com.irojas.demojwt.Contact;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irojas.demojwt.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String alias;
    private String bankInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User userEmail;

    public Contact(){}

}
