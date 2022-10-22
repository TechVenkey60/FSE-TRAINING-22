package com.jwt.token.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {


    @GetMapping("/test")
    public String getHome(Principal principal){
        return "HI "+ principal.getName()+". Welcome to JWT Authentication Demo";
    }
}
