package com.vrk.tms.service.service;

import com.vrk.tms.service.entity.TransactionDetails;
import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;

import java.util.List;

public interface ITransactionBankService {
    UserRegistration createNewUserAccount(NewUserData newUserData);
    UserRegistration validateAndGetUser(SignIn signIn);
    UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails);
    String validateAnsSaveTransaction(TransactionInput transactionInput);
    List<TransactionDetails> fetchUserTransactions(String accountNumber, String orderBy);

    UserRegistration fetchUserInfo(String accountNumber);
}
