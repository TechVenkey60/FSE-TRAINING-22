package com.vrk.bank.portal.service;

import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;

public interface IBankService {
    UserRegistration createNewUserAccount(NewUserData newUserData);
    UserRegistration validateAndGetUser(SignIn signIn);
    UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails);
}
