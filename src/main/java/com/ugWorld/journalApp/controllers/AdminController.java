package com.ugWorld.journalApp.controllers;

import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all= userService.getAll();
        if (all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);

    }
    @PostMapping("create-admin")
    public void  CreateUser(@RequestBody User user){
        userService.saveAdmin(user);

    }
}
