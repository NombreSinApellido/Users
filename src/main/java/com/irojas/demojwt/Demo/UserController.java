package com.irojas.demojwt.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    @Autowired    
    private UserService uService;

    @GetMapping("/{username}")
    public ResponseEntity <?> showUserInfo(@PathVariable String username ){
        
        if (uService.usernameExist(username) && uService.isActive(username) ) {
            return ResponseEntity.ok(uService.showUserById(username));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user was found with the email provided: " + username);
    }
    
    @PutMapping("/{username}")
    @Transactional
    public ResponseEntity<?> updateUserInfo(@RequestBody UserUpdateDTO updateDTO){
        
        if (uService.usernameExist(updateDTO.username()) && uService.isActive(updateDTO.username())) {
            uService.updateUser(updateDTO);
            return ResponseEntity.ok("User info was succesfully updated");            
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user provided was not found: " 
        + updateDTO.username());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> logicalDelete(@PathVariable String username){
        uService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
