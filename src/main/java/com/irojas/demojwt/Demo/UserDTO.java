package com.irojas.demojwt.Demo;

import java.time.LocalDate;


public record UserDTO(
    String lastname,
    String firstname,
    String phonenumber,
    String address,
    LocalDate dateBirth
) {
}
