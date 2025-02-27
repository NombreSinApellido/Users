package com.irojas.demojwt.Demo;


public record UserUpdateDTO(
    @org.springframework.lang.NonNull
    String username,
    String phonenumber,
    String address
) {
    
}
