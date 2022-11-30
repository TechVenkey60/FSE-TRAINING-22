package com.vrk.tms.service.controller;

import com.vrk.tms.service.entity.TransactionDetails;
import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;
import com.vrk.tms.service.service.ITransactionBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionBankService bankServiceImpl;

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
        return  new ResponseEntity<>(updatedUserData, HttpStatus.OK);
    }


    @PostMapping("/save/transaction")
    public ResponseEntity<String>  saveUserTransaction(@RequestBody TransactionInput transactionInput){
        var status = bankServiceImpl.validateAnsSaveTransaction(transactionInput);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }


    @GetMapping("/user/{accountNumber}")
    public ResponseEntity<List<TransactionDetails>> getTransactionDetails(@PathVariable String accountNumber,
                                                                          @RequestParam(defaultValue = "ASC") String orderBy){

        return  null;
    }
}
