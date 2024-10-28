package com.ugWorld.journalApp.controllers;

import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.repository.UserRepository;
import com.ugWorld.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName= authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
//    @DeleteMapping
//    public ResponseEntity<?> updateUser(){
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        userService.deleteByUserName(authentication.getName());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//}
 }