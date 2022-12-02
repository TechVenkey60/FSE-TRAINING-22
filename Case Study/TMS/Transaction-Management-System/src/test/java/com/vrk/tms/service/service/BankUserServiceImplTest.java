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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankUserServiceImplTest {

    @InjectMocks
    private TransactionBankUserServiceImpl bankUserService;
    @Mock
    private UserRegistryRepository userRegistryRepository;
    @Mock
    private TransactionRepository transactionRepository;


    @Test
    void createNewUserAccountTest() {

        when(userRegistryRepository.loadUserByUserNameAndEmail(any(), any()))
                .thenReturn(null);
        when(userRegistryRepository.save(any()))
                .thenReturn(getRegisteredUserDetails());

        var expectedUserData = bankUserService.createNewUserAccount(getNewAccountUserData());
        assertEquals(expectedUserData.getEmail(), getNewAccountUserData().getEmail());

    }

    @Test
    void createNewUserAccountTestForAvailableUserName() {
        when(userRegistryRepository.loadUserByUserNameAndEmail(any(), any()))
                .thenReturn(getRegisteredUserDetails());

        var newUser = getNewAccountUserData();
        assertThrows(InvalidDataException.class,
                () -> bankUserService.createNewUserAccount(newUser));

    }

    @Test
    void createNewUserAccountTestForAvailableEmail() {

        var inputData = getRegisteredUserDetails();
        inputData.setUserName("Venkatesh1234");
        when(userRegistryRepository.loadUserByUserNameAndEmail(any(), any()))
                .thenReturn(inputData);

        var newAccountUserData = getNewAccountUserData();
        assertThrows(InvalidDataException.class,
                () -> bankUserService.createNewUserAccount(newAccountUserData));

    }


    @Test
    void validateAndGetUserTest() {
        when(userRegistryRepository.loadUserByUserName(any()))
                .thenReturn(getRegisteredUserDetails());

        assertNotNull(bankUserService.validateAndGetUser(getLoginData()));
    }

    @Test
    void validateAndGetUserTest_WrongUserName() {
        when(userRegistryRepository.loadUserByUserName(any()))
                .thenReturn(null);
        SignIn data = getLoginData();
        assertThrows(DataNotFoundException.class,
                () -> bankUserService.validateAndGetUser(data));
    }

    @Test
    void validateAndGetUserTest_WrongPassword() {
        when(userRegistryRepository.loadUserByUserName(any()))
                .thenReturn(getRegisteredUserDetails());

        SignIn loginData = getLoginData();
        loginData.setPassword("1212131");

        assertThrows(DataNotFoundException.class,
                () -> bankUserService.validateAndGetUser(loginData));

    }

    @Test
    void updateAccountDetailsTest() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(getRegisteredUserDetails());

        when(userRegistryRepository.saveAndFlush(any()))
                .thenReturn(getRegisteredUserDetails());

        var updatedUserData = bankUserService.updateAccountDetails(getUpdateAccountDetails());
        assertNotNull(updatedUserData);
    }

    @Test
    void updateAccountDetailsTest_NoUserDataFound() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(null);

        var updatedUserData = getUpdateAccountDetails();
        assertThrows(DataNotFoundException.class, () -> bankUserService.updateAccountDetails(updatedUserData));
    }


    @Test
    void validateAnsSaveTransactionTest() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(getRegisteredUserDetails());

        when(transactionRepository.save(any())).thenReturn(getTransactionDetails());
        when(userRegistryRepository.saveAndFlush(any())).thenReturn(getRegisteredUserDetails());

        var status = bankUserService.validateAnsSaveTransaction(getTransactionInput());
        assertNotNull(status);
    }

    @Test
    void validateAnsSaveTransactionTest_No_ReceiverAccount() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(null);

       var input = getTransactionInput();
       assertThrows(DataNotFoundException.class, () -> bankUserService.validateAnsSaveTransaction(input));
    }

    @Test
    void validateAnsSaveTransactionTest_Insufficient_Amount() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(getRegisteredUserDetails());

        var transactionInput = getTransactionInput();
        transactionInput.setTrxAmount(2000000.0);
        assertThrows(InvalidDataException.class, () -> bankUserService.validateAnsSaveTransaction(transactionInput));
    }

    @Test
    void fetchUserInfoTest() {
        when(userRegistryRepository.getUserByAccountNumber(any()))
                .thenReturn(getRegisteredUserDetails());

        var expectedData = bankUserService.fetchUserInfo("1213121213");
        assertNotNull(expectedData);
    }

    @Test
    void fetchUserTransactionsTest_In_ASC_Order() {
        when(transactionRepository.loadTransactionDetailsByAccountNumberInASCOrder(any()))
                .thenReturn(new ArrayList<>());
        assertNotNull(bankUserService.fetchUserTransactions("392734923","ASC"));
    }

    @Test
    void fetchUserTransactionsTest_In_DESC_Order() {
        when(transactionRepository.loadTransactionDetailsByAccountNumberInDESCOrder(any())).thenReturn(new ArrayList<>());
        assertNotNull(bankUserService.fetchUserTransactions("2374878343","DESC"));

    }

    private NewUserData getNewAccountUserData() {

        return NewUserData.builder()
                .fullName("Venkatesh Kokkanti")
                .userName("venkat123")
                .password("password")
                .email("Venkat12@gmail.com")
                .dob(new Date(System.currentTimeMillis()))
                .accountType("Saving")
                .panCardNumber("FPMPK8869T")
                .contactNumber("9876543234")
                .address("Rayachoty")
                .state("AP")
                .country("India").build();

    }

    private UserRegistration getRegisteredUserDetails() {

        return UserRegistration.builder()
                .fullName("Venkatesh Kokkanti")
                .userName("venkat123")
                .password("venkat123")
                .email("Venkat12@gmail.com")
                .dob(new Date(System.currentTimeMillis()))
                .accountType("Saving")
                .panCardNumber("FPMPK8869T")
                .contactNumber("9876543234")
                .address("Rayachoty,")
                .state("AP")
                .country("India")
                .balance(5000.0)
                .build();
    }

    private SignIn getLoginData() {
        return new SignIn("venkat", "venkat123");
    }

    private UpdateAccountDetails getUpdateAccountDetails() {
        return UpdateAccountDetails.builder()
                .accountNumber("12131")
                .fullName("Venkatesh")
                .password("Venkatesh123")
                .address("Rayachoty,Madanapalli Raod")
                .state("AP")
                .country("India")
                .contactNumber("9898989891")
                .build();
    }

    private TransactionInput getTransactionInput(){
        return new TransactionInput("12121",2000.0,"debit",200.0,"212121",new Date());
    }


    private TransactionDetails getTransactionDetails(){
       var transactionDetails = new TransactionDetails();
       transactionDetails.setTransactionId(1);
       transactionDetails.setAccountNumber("12131212");
       transactionDetails.setAmount(3000.0);
       transactionDetails.setTrxType("Debit");
       transactionDetails.setTrxAmount(2000.0);
       transactionDetails.setReceiverAccountNumber("121121312");
       transactionDetails.setDate(new Date());
       transactionDetails.setStatus("Debited");

       return transactionDetails;
    }
}
