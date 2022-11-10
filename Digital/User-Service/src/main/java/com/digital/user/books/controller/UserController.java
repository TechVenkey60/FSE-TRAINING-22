package com.digital.user.books.controller;


import com.digital.user.books.entity.User;
import com.digital.user.books.model.SignInRequest;
import com.digital.user.books.model.SignUpRequest;
import com.digital.user.books.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<User> validateUser(@RequestBody SignInRequest signInRequest){
        var user = userService.validateAndGetUser(signInRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<User> createUser(@RequestBody SignUpRequest signUpRequest){
        var user = userService.validateAndCreateUser(signUpRequest);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
