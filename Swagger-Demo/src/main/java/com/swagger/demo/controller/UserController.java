package com.swagger.demo.controller;

import com.swagger.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/getUsersData")
    public List<User> getUsers(){
        return Arrays.asList(new User(1,"Venkat", "2323232"),
                new User(2,"Suman", "4343423"));
    }

    @GetMapping("/user/{userName}")
    public User getUsers(@PathVariable String userName){
        return new User(1,"Venkat", "2323232");
    }
}
