package com.vrk.bank.portal.service;

import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.handlers.DataNotFoundException;
import com.vrk.bank.portal.handlers.InvalidDataException;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;
import com.vrk.bank.portal.repository.LoanRepository;
import com.vrk.bank.portal.repository.UserRegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class BankUserServiceImpl implements IBankService {


    private final UserRegistryRepository userRegistryRepository;
    private final LoanRepository loanRepository;

    @Transactional
    @Override
    public UserRegistration createNewUserAccount(NewUserData newUserData) {

        log.debug(" Entered into BankUserServiceImpl::createNewUserAccount method");

        var existedUser = userRegistryRepository.loadUserByUserNameAndEmail(newUserData.getUserName(), newUserData.getEmail());
        validateNewUserInput(newUserData.getUserName(), newUserData.getEmail(), existedUser);

        UserRegistration userRegistration = new UserRegistration();
        mapInputDataToEntity(userRegistration, newUserData);

        var persistedUser = userRegistryRepository.save(userRegistration);

        log.debug("Existed from BankUserServiceImpl::createNewUserAccount method");

        return persistedUser;
    }

    @Transactional
    @Override
    public UserRegistration validateAndGetUser(SignIn signIn) {

        log.debug(" Entered into BankUserServiceImpl::validateAndGetUser method");
        var existedUser = userRegistryRepository.loadUserByUserName(signIn.getUserName());

        if (isNull(existedUser) || !signIn.getPassword().equals(existedUser.getPassword())) {
            log.error("Found InValid Credentials..");
            throw new DataNotFoundException("InValid userName/Password...", 404);
        }
        log.debug("Returned User Details From BankUserServiceImpl::validateAndGetUser method");
        return existedUser;
    }

    @Transactional
    @Override
    public UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails) {
        log.info("Entered into BankUserServiceImpl::updateAccountDetails method");

        var existedUser = userRegistryRepository.getUserByAccountNumber(accountDetails.getAccountNumber());

        if (isNull(existedUser)) {
            log.error("No User Data Available.");
            throw new DataNotFoundException("User is not available", 404);
        }

        mapAccountDetailsToEntity(existedUser, accountDetails);
        var updatedUserEntity = userRegistryRepository.saveAndFlush(existedUser);

        log.info("Updated User Account Details Successfully..!");
        return updatedUserEntity;
    }

    private void validateNewUserInput(String userName, String email, UserRegistration existedUser) {
        if (nonNull(existedUser)) {
            if (userName.equalsIgnoreCase(existedUser.getUserName())) {
                log.debug("UserName is already available..");
                throw new InvalidDataException(String.format("UserName: %s is already available..", userName));
            } else if (email.equalsIgnoreCase(existedUser.getEmail())) {
                log.debug("Email is already available..");
                throw new InvalidDataException(String.format("Email: %s is already available..", email));
            }
        }
    }

    private void mapInputDataToEntity(UserRegistration userRegistration,
                                      NewUserData newUserData) {

        userRegistration.setFullName(newUserData.getFullName());
        userRegistration.setUserName(newUserData.getUserName());
        userRegistration.setPassword(newUserData.getPassword());
        userRegistration.setEmail(newUserData.getEmail());
        userRegistration.setDob(newUserData.getDob());
        userRegistration.setAccountType(newUserData.getAccountType());
        userRegistration.setPanCardNumber(newUserData.getPanCardNumber());
        userRegistration.setAddress(newUserData.getAddress());
        userRegistration.setState(newUserData.getState());
        userRegistration.setCountry(newUserData.getCountry());
        userRegistration.setContactNumber(newUserData.getContactNumber());
        userRegistration.setAccountNumber(generateNewAccountNumber());
    }

    private String generateNewAccountNumber() {
        var localDateTime = LocalDateTime.now();

        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();

        StringBuilder builder = new StringBuilder();
        builder.append("3210")
                .append(year)
                .append(monthValue)
                .append(dayOfMonth)
                .append(minute)
                .append(second);

        return builder.toString();
    }

    private void mapAccountDetailsToEntity(UserRegistration existedUser,
                                           UpdateAccountDetails accountDetails) {

        existedUser.setFullName(accountDetails.getFullName());
        existedUser.setPassword(accountDetails.getPassword());
        existedUser.setAddress(accountDetails.getAddress());
        existedUser.setState(accountDetails.getState());
        existedUser.setCountry(accountDetails.getCountry());
        existedUser.setContactNumber(accountDetails.getContactNumber());
    }
}
