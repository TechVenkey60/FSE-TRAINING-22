package com.vrk.bank.portal.service;

import com.vrk.bank.portal.entity.LoanDetails;
import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;
import com.vrk.bank.portal.model.UserLoanDto;

import java.util.List;

public interface IBankService {
    UserRegistration createNewUserAccount(NewUserData newUserData);
    UserRegistration validateAndGetUser(SignIn signIn);
    UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails);
    List<LoanDetails> applyForLoan(UserLoanDto userLoan);
}
