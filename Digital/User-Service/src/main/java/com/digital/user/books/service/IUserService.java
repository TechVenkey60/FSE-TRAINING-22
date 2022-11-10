package com.digital.user.books.service;

import com.digital.user.books.entity.User;
import com.digital.user.books.model.SignInRequest;
import com.digital.user.books.model.SignUpRequest;

public interface IUserService {
    User validateAndGetUser(SignInRequest signInRequest);

    User validateAndCreateUser(SignUpRequest signUpRequest);
}
