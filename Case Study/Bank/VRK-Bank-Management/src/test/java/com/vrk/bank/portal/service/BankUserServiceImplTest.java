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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static com.vrk.bank.portal.service.BankUserServiceImpl.APPLIED_LOAN_SUCCESSFULLY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankUserServiceImplTest {

    @InjectMocks
    private BankUserServiceImpl bankUserService;
    @Mock
    private UserRegistryRepository userRegistryRepository;
    @Mock
    private LoanRepository loanRepository;


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
    void getAppliedLoanDetailsTest() {
        when(loanRepository.fetchByAccountNumber(any()))
                .thenReturn(new ArrayList<>());

        var actualData = bankUserService.getAppliedLoanDetails("123445989");
        assertNotNull(actualData);
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
    void applyForLoanTest() {
        when(loanRepository.save(any())).thenReturn(getLoanDetails());
        var expectedData = bankUserService.applyForLoan(getLoanDetailsInput());
        assertNotNull(expectedData);
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
                .address("Rayachoty")
                .state("AP")
                .country("India").build();
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

    private UserLoanDto getLoanDetailsInput() {
        return new UserLoanDto("1213",
                "HomeLoan", 1000.0,
                new Date(System.currentTimeMillis()),
                10.0,
                2);
    }

    private LoanDetails getLoanDetails(){
        return LoanDetails.builder()
                .loadId(1)
                .accountNumber("12131212")
                .loanType("HomeLoan")
                .loanAmount(10000.0)
                .loanAppliedDate(new Date())
                .roi(10.0)
                .durationOfLoan(1)
                .emi(10000L)
                .build();
    }
}
