package com.vrk.bank.portal.service;

import com.vrk.bank.portal.entity.LoanDetails;
import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.handlers.DataNotFoundException;
import com.vrk.bank.portal.handlers.InvalidDataException;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;
import com.vrk.bank.portal.model.UserLoanDto;
import com.vrk.bank.portal.repository.LoanRepository;
import com.vrk.bank.portal.repository.UserRegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class BankUserServiceImpl implements IBankService {
    public static final String APPLIED_LOAN_SUCCESSFULLY = "User Applied Loan Successfully..!";
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

    @Transactional
    @Override
    public String applyForLoan(UserLoanDto userLoan) {
        log.info("Entered Into BankUserServiceImpl::applyForLoan method");

        var monthlyEmiValue = validateAndUpdateMonthlyEmi(userLoan);
        var loanDetails = new LoanDetails();
        loanDetails.setEmi(Math.round(monthlyEmiValue));
        mapLoanDetailsToEntity(loanDetails, userLoan);

        var issuedLoanDetails = loanRepository.save(loanDetails);
        log.info("Loan has been applied and approved for accountNumber :" + issuedLoanDetails.getAccountNumber());

        return APPLIED_LOAN_SUCCESSFULLY;
    }

    @Override
    public List<LoanDetails> getAppliedLoanDetails(String accountNumber) {
        log.info("Entered Into BankUserServiceImpl::getAppliedLoanDetails method");
        var existedLoanDetails = loanRepository.fetchByAccountNumber(accountNumber);
        log.info("Returned Applied LoanDetails Successfully..!");
        return existedLoanDetails;
    }

    private void validateNewUserInput(String userName, String email, UserRegistration existedUser) {
        if (nonNull(existedUser)) {
            if (userName.equalsIgnoreCase(existedUser.getUserName())) {
                log.debug("UserName is already available..");
                throw new InvalidDataException(String.format("UserName: %s is already available..", userName));
            }

            if (email.equalsIgnoreCase(existedUser.getEmail())) {
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
        StringBuilder builder = new StringBuilder();
        builder.append("223").append(System.currentTimeMillis());
        return builder.toString();
    }

    private void mapAccountDetailsToEntity(UserRegistration existedUser,
                                           UpdateAccountDetails accountDetails) {
        existedUser.setFullName(accountDetails.getFullName());
        existedUser.setPassword(accountDetails.getPassword());
        existedUser.setEmail(accountDetails.getEmail());
        existedUser.setAddress(accountDetails.getAddress());
        existedUser.setState(accountDetails.getState());
        existedUser.setCountry(accountDetails.getCountry());
        existedUser.setContactNumber(accountDetails.getContactNumber());
    }

    private Double validateAndUpdateMonthlyEmi(UserLoanDto userLoan) {

        var principal = userLoan.getLoanAmount();
        var rate = userLoan.getRoi() / (12 * 100);
        var time = userLoan.getDurationOfLoan() * 12;

        return (principal * rate * Math.pow(1 + rate, time)) / (Math.pow(1 + rate, time) - 1);

    }


    private void mapLoanDetailsToEntity(LoanDetails loanDetails,
                                        UserLoanDto userLoan) {
        loanDetails.setAccountNumber(userLoan.getAccountNumber());
        loanDetails.setLoanType(userLoan.getLoanType());
        loanDetails.setLoanAmount(userLoan.getLoanAmount());
        loanDetails.setLoanAppliedDate(userLoan.getLoanAppliedDate());
        loanDetails.setRoi(userLoan.getRoi());
        loanDetails.setDurationOfLoan(userLoan.getDurationOfLoan());
    }
}
