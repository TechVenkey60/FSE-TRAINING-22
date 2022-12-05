package com.vrk.tms.service.controller;

import com.vrk.tms.service.entity.TransactionDetails;
import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;
import com.vrk.tms.service.service.ITransactionBankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final ITransactionBankService bankServiceImpl;

    @PostMapping("/newUserAccount")
    public ResponseEntity<UserRegistration> createNewUser(@RequestBody NewUserData newUserData){

        log.info("Entered Into TransactionController::createNewUser method.");
        var user = bankServiceImpl.createNewUserAccount(newUserData);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegistration> userSign(@RequestBody SignIn signIn){
        log.info("Entered Into TransactionController::userSign method.");
        var persistedUser = bankServiceImpl.validateAndGetUser(signIn);
        return new ResponseEntity<>(persistedUser, HttpStatus.OK);
    }

    @PutMapping("/update/accountDetails")
    public ResponseEntity<UserRegistration> updateUserAccountDetails(@RequestBody UpdateAccountDetails accountDetails){
        log.info("Entered Into TransactionController::updateUserAccountDetails method.");
        var updatedUserData = bankServiceImpl.updateAccountDetails(accountDetails);
        return  new ResponseEntity<>(updatedUserData, HttpStatus.OK);
    }


    @PostMapping("/save/transaction")
    public ResponseEntity<String>  saveUserTransaction(@RequestBody TransactionInput transactionInput){

        log.info("Entered Into TransactionController::saveUserTransaction method.");
        var status = bankServiceImpl.validateAnsSaveTransaction(transactionInput);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }


    @GetMapping("/user/{accountNumber}")
    public ResponseEntity<List<TransactionDetails>> getTransactionDetails(@PathVariable String accountNumber,
                                                                          @RequestParam(defaultValue = "ASC") String sortBy){
        log.info("Entered Into TransactionController::getTransactionDetails method.");

        var transactionDetails = bankServiceImpl.fetchUserTransactions(accountNumber,sortBy);
        return  new ResponseEntity<>(transactionDetails,HttpStatus.OK);
    }

    @GetMapping("/registered/{accountNumber}")
    public ResponseEntity<UserRegistration> getRegisteredUserDetails(@PathVariable String accountNumber){
        log.info("Entered Into TransactionController::getRegisteredUserDetails method.");
        var transactionDetails = bankServiceImpl.fetchUserInfo(accountNumber);
        return  new ResponseEntity<>(transactionDetails,HttpStatus.OK);
    }
}
