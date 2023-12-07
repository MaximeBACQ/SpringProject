package com.jeeSpring.Controller;

import com.jeeSpring.Exceptions.UserExistenceException;
import com.jeeSpring.Model.User;
import com.jeeSpring.Business.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    public void createUser(User user) throws UserExistenceException{
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userService.createUser(user);
    }


    public User getUserById(Long userId) throws UserExistenceException {
        return userService.getUserById(userId);
    }


    public void updateUser(User user) {
        userService.updateUser(user);
    }


    public void deleteUser(Long userId){
        userService.deleteUser(userId);
    }


    public User authenticateUser(String email, String password) {
        return userService.authenticateUser(email, password);
    }


    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }
}
