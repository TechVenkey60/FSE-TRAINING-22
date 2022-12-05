package com.vrk.tms.service.service;

import com.vrk.tms.service.entity.TransactionDetails;
import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.handlers.DataNotFoundException;
import com.vrk.tms.service.handlers.InvalidDataException;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;
import com.vrk.tms.service.repository.TransactionRepository;
import com.vrk.tms.service.repository.UserRegistryRepository;
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
public class TransactionBankUserServiceImpl implements ITransactionBankService {

    public static final String CREDITED = "Credited From";
    public static final String DEBITED = "Debited To";
    public static final String AMOUNT_HAS_BEEN_TRANSFERRED_SUCCESSFULLY = "Amount has been transferred successfully!";


    private final UserRegistryRepository userRegistryRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    @Override
    public UserRegistration createNewUserAccount(NewUserData newUserData) {
        log.debug(" Entered into TransactionBankUserServiceImpl::createNewUserAccount method");

        var existedUser = userRegistryRepository.loadUserByUserNameAndEmail(newUserData.getUserName(), newUserData.getEmail());
        validateNewUserInput(newUserData.getUserName(), newUserData.getEmail(), existedUser);

        UserRegistration userRegistration = new UserRegistration();
        mapInputDataToEntity(userRegistration, newUserData);

        var persistedUser = userRegistryRepository.save(userRegistration);

        log.debug("Existed from TransactionBankUserServiceImpl::createNewUserAccount method");
        return persistedUser;
    }

    @Transactional
    @Override
    public UserRegistration validateAndGetUser(SignIn signIn) {

        log.debug(" Entered into TransactionBankUserServiceImpl::validateAndGetUser method");
        var existedUser = userRegistryRepository.loadUserByUserName(signIn.getUserName());

        if (isNull(existedUser) || !signIn.getPassword().equals(existedUser.getPassword())) {
            log.error("Found InValid Credentials..");
            throw new DataNotFoundException("InValid userName/Password...", 404);
        }
        log.debug("Returned User Details From TransactionBankUserServiceImpl::validateAndGetUser method");
        return existedUser;
    }

    @Transactional
    @Override
    public UserRegistration updateAccountDetails(UpdateAccountDetails accountDetails) {
        log.info("Entered into TransactionBankUserServiceImpl::updateAccountDetails method");

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
    public String validateAnsSaveTransaction(TransactionInput transactionInput) {
        log.info("Entered into TransactionBankUserServiceImpl::validateAnsSaveTransaction method");

        var receiverAccountData = userRegistryRepository.getUserByAccountNumber(transactionInput.getReceiverAccountNumber());

        if(isNull(receiverAccountData)){
            throw new DataNotFoundException("Receiver Account Details Not Found..",404);
        }

        if(transactionInput.getTrxAmount() > transactionInput.getAmount()){
            throw new InvalidDataException("Insufficient Funds Transfer Found...");
        }

        var senderAccountData = userRegistryRepository.getUserByAccountNumber(transactionInput.getAccountNumber());

        var debitTransactionDetails = new TransactionDetails();
        debitAmountFromSender(debitTransactionDetails,transactionInput,senderAccountData);
        var persistedSenderTransaction = transactionRepository.save(debitTransactionDetails);

        senderAccountData.setBalance(persistedSenderTransaction.getAmount());
        userRegistryRepository.saveAndFlush(senderAccountData);


        var creditTransactionDetails = new TransactionDetails();
        creditAmountToReceiverAccount(creditTransactionDetails,transactionInput,receiverAccountData);
        var persistedReceiverTransaction = transactionRepository.save(creditTransactionDetails);

        receiverAccountData.setBalance(persistedReceiverTransaction.getAmount());
        userRegistryRepository.saveAndFlush(receiverAccountData);

        log.info("Amount has been credited to receiver account successfully.");
        return AMOUNT_HAS_BEEN_TRANSFERRED_SUCCESSFULLY;
    }

    @Override
    public List<TransactionDetails> fetchUserTransactions(String accountNumber, String sortBy) {
        log.info("Entered into TransactionBankUserServiceImpl::fetchUserTransactions method");

        if("ASC".equalsIgnoreCase(sortBy)){
            return transactionRepository.loadTransactionDetailsByAccountNumberInASCOrder(accountNumber);
        }else {
            return transactionRepository.loadTransactionDetailsByAccountNumberInDESCOrder(accountNumber);
        }
    }

    @Override
    public UserRegistration fetchUserInfo(String accountNumber) {
        log.info("Entered into TransactionBankUserServiceImpl::fetchUserInfo method");
        return userRegistryRepository.getUserByAccountNumber(accountNumber);
    }


    private void validateNewUserInput(String userName, String email, UserRegistration existedUser) {
        if (nonNull(existedUser)) {
            if (userName.equalsIgnoreCase(existedUser.getUserName())) {
                log.error("UserName is already available..");
                throw new InvalidDataException(String.format("UserName: %s is already available..", userName));
            }

            if (email.equalsIgnoreCase(existedUser.getEmail())) {
                log.error("Email is already available..");
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
        userRegistration.setBalance(newUserData.getBalance());
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


    private void debitAmountFromSender(TransactionDetails transactionDetails,
                                       TransactionInput transactionInput,
                                       UserRegistration senderAccountData) {

        transactionDetails.setAccountNumber(transactionInput.getAccountNumber());
        var amount = senderAccountData.getBalance() - transactionInput.getTrxAmount();
        transactionDetails.setAmount(amount);
        transactionDetails.setTrxAmount(transactionInput.getTrxAmount());
        transactionDetails.setTrxType(DEBITED);
        transactionDetails.setReceiverAccountNumber(transactionInput.getReceiverAccountNumber());
        transactionDetails.setDate(transactionInput.getDate());
        transactionDetails.setStatus(DEBITED);

    }

    private void creditAmountToReceiverAccount(TransactionDetails transactionDetails,
                                               TransactionInput transactionInput,
                                               UserRegistration receiverAccountData) {

        transactionDetails.setAccountNumber(transactionInput.getReceiverAccountNumber());
        var amount =  receiverAccountData.getBalance() + transactionInput.getTrxAmount();
        transactionDetails.setAmount(amount);
        transactionDetails.setTrxAmount(transactionInput.getTrxAmount());
        transactionDetails.setTrxType(CREDITED);
        transactionDetails.setReceiverAccountNumber(transactionInput.getAccountNumber());
        transactionDetails.setDate(transactionInput.getDate());
        transactionDetails.setStatus(CREDITED);
    }
}
