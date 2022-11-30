package com.vrk.tms.service.service;

import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;

public interface ITransactionBankService {
    UserRegistration createNewUserAccount(NewUserData newUserData);
    UserRegistration validateAndGetUser(SignIn signIn);
    UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails);
    String validateAnsSaveTransaction(TransactionInput transactionInput);
}
