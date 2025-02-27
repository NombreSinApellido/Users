package com.irojas.demojwt.Demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irojas.demojwt.User.User;
import com.irojas.demojwt.User.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepository;



    public UserDTO showUserById(String username) {
        Optional<User> user = uRepository.findByUsername(username);
        if (user.isPresent()) {
            User u = user.get();
            return new UserDTO(u.getFirstname(), u.getLastname(), u.getPhonenumber(),
             u.getAddress(), u.getDateBirth());
        }
        return null;
    }

    public boolean usernameExist (String username){
        Optional<User> user = uRepository.findByUsername(username);
        if (user.isPresent()) {
            return true;            
        }
        return false;
    }

    public boolean isActive(String username) {
        Optional<User> user = uRepository.findByActiveTrueAndUsername(username);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    public User findUser(String username) {
        return uRepository.getReferenceById(username);
    }

    public void deleteUser(String username) {
        User user = findUser(username);
        user.desactivarMedico();
        uRepository.save(user);
    }

    public void updateUser(UserUpdateDTO updateDTO) {
        User user = findUser(updateDTO.username());
        user.updateInfo(updateDTO);
        uRepository.save(user);
    }


}
