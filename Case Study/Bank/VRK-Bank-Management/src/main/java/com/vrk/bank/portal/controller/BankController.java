package com.vrk.bank.portal.controller;

import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;
import com.vrk.bank.portal.service.IBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BankController {

    private final IBankService bankServiceImpl;

    @PostMapping("/newUserAccount")
    public ResponseEntity<UserRegistration> createNewUser(@RequestBody NewUserData newUserData){
        var user = bankServiceImpl.createNewUserAccount(newUserData);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegistration> userSign(@RequestBody SignIn signIn){
        var persistedUser = bankServiceImpl.validateAndGetUser(signIn);
        return new ResponseEntity<>(persistedUser, HttpStatus.OK);
    }


    @PutMapping("/update/accountDetails")
    public ResponseEntity<UserRegistration> updateUserAccountDetails(@RequestBody UpdateAccountDetails accountDetails){
        var updatedUserData = bankServiceImpl.updateAccountDetails(accountDetails);
        return  new ResponseEntity<>(updatedUserData,HttpStatus.OK);
    }

}
