package com.awd.test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsTestController {

    @GetMapping("/welcome")
    public String hello(){
        return "Hello Venkat. Welcome to EC2 Service to deploy your app.";
    }
}
