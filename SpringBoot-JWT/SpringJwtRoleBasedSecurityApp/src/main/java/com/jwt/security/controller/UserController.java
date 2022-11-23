package com.jwt.security.controller;


import com.jwt.security.entity.User;
import com.jwt.security.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostConstruct
    public void initDataStore(){
        userService.initUserAndAdminRoles();
    }

    @PostMapping("/registerNewUser")
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        return  new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }


    @GetMapping("/adminData")
    public String adminMessage(){
        return "This url is only accessable for Admin Role";
    }


    @GetMapping("/userData")
    public String userMessage(){
        return "This url is only accessable for User Role...";
    }


}
