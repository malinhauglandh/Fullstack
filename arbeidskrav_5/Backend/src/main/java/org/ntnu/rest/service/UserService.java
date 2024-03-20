package org.ntnu.rest.service;

import org.ntnu.rest.entity.Calculation;
import org.ntnu.rest.entity.User;
import org.ntnu.rest.model.UserLogin;
import org.ntnu.rest.repository.UserRepository;
import org.ntnu.rest.security.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public boolean createUser(UserLogin login){
        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(PasswordUtils.encode(login.getPassword()));
        if(userRepository.findById(user.getUsername()).isPresent()){
            return false;
        }
        userRepository.save(user);
        return true;

    }

    public boolean login(UserLogin login){
        Optional<User> user = userRepository.findById(login.getUsername());
        return user.filter(value -> PasswordUtils.matches(login.getPassword(), value.getPassword())).isPresent();

    }

}