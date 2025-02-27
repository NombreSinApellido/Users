package com.irojas.demojwt.Auth;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String lastname;
    String firstname;
    String phonenumber;
    String address;
    String password;
    LocalDate dateBirth;
}
