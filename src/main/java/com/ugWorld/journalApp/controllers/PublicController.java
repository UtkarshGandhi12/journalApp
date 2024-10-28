package com.ugWorld.journalApp.controllers;

import com.ugWorld.journalApp.entity.User;
import com.ugWorld.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("health-check")
    public String healthCheck(){
        return "ok";
    }
    @PostMapping("create-user")
    public String  CreateUser(@RequestBody User user){
        userService.saveNewUser(user);
        return "ok";
    }

}
