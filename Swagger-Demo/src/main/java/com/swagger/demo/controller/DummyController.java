package com.swagger.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DummyController {

    @GetMapping("/test")
    public String getMessage(){
        return "Hello, Welcome to Swagger UI.";
    }

    @PostMapping("/postData")
    public String postMessage(@RequestBody final String name){
        return "Hello "+name+". Welcome to Swagger UI.";
    }

    @PutMapping("/putData")
    public String putMessage(@RequestBody final String name){
        return "Hello "+name+". Welcome to Swagger UI.";
    }
}
