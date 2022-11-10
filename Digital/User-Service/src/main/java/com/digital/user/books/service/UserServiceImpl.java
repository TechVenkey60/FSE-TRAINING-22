package com.digital.user.books.service;

import com.digital.user.books.entity.User;
import com.digital.user.books.handlers.DataNotFoundException;
import com.digital.user.books.handlers.InvalidDataException;
import com.digital.user.books.model.SignInRequest;
import com.digital.user.books.model.SignUpRequest;
import com.digital.user.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateAndGetUser(SignInRequest signInRequest) {
        var user = userRepository.loadByEmail(signInRequest.getEmail());

        if (isNull(user) || !signInRequest.getPassword().equalsIgnoreCase(user.getPassword())) {
            throw new DataNotFoundException("InValid User/password", 404);
        }
        return user;
    }

    @Override
    public User validateAndCreateUser(SignUpRequest signUpRequest) {

        var userByEmail = userRepository.loadByEmail(signUpRequest.getEmail());
        if (!isNull(userByEmail)) {
            throw new InvalidDataException("Email is registered already.");
        }
        var userByUserName = userRepository.loadUserByUserName(signUpRequest.getUserName());
        if (!isNull(userByUserName)) {
            throw new InvalidDataException("UserName is registered already.");
        }

        User user = new User();
        mapRequestToEntity(user, signUpRequest);
        var savedUser = userRepository.save(user);
        return savedUser;
    }

    private void mapRequestToEntity(User user, SignUpRequest signUpRequest) {
        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(signUpRequest.getRole());
    }
}
