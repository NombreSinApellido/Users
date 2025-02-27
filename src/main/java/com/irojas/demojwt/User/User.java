package com.irojas.demojwt.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.irojas.demojwt.Contact.Contact;
import com.irojas.demojwt.Demo.UserUpdateDTO;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @Basic
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String lastname;
    String firstname;
    String phonenumber;
    String address;
    String password;
    LocalDate dateBirth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEmail", orphanRemoval = true)
    private  List<Contact> contacts;
    @Enumerated(EnumType.STRING) 
    Role role;
    @Builder.Default
    boolean active = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void desactivarMedico() {
        this.active = false;
    }

    public void updateInfo (UserUpdateDTO userUpdateDTO){
        if (userUpdateDTO.phonenumber() != null) {
            this.phonenumber = userUpdateDTO.phonenumber();
        }
        if (userUpdateDTO.address() != null) {
            this.address = userUpdateDTO.address();
        }
    }
}
