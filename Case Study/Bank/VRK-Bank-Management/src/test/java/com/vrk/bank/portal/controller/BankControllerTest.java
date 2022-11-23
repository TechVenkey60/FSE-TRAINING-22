package com.vrk.bank.portal.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrk.bank.portal.entity.UserRegistration;
import com.vrk.bank.portal.model.NewUserData;
import com.vrk.bank.portal.model.SignIn;
import com.vrk.bank.portal.model.UpdateAccountDetails;
import com.vrk.bank.portal.model.UserLoanDto;
import com.vrk.bank.portal.service.IBankService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IBankService bankServiceImpl;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void createNewUserTest() {
        when(bankServiceImpl.createNewUserAccount(any()))
                .thenReturn(getRegisteredUserDetails());

        mockMvc.perform(post("/newUserAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getNewAccountUserData())))
                .andExpect(status().isCreated());
    }


    @SneakyThrows
    @Test
    void userSignTest() {
        when(bankServiceImpl.validateAndGetUser(any()))
                .thenReturn(getRegisteredUserDetails());

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getLoginData())))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void updateUserAccountDetailsTest() {
        when(bankServiceImpl.updateAccountDetails(any()))
                .thenReturn(getRegisteredUserDetails());


        mockMvc.perform(put("/update/accountDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getUpdateAccountDetails())))
                .andExpect(status().isOk());
    }


    @SneakyThrows
    @Test
    void applyLoanTest() {
        when(bankServiceImpl.applyForLoan(any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/applyLoan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getLoanDetailsInput())))
                .andExpect(status().isOk());
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

    private SignIn getLoginData(){
        return  new SignIn("venkat","venkat12");
    }

    private UpdateAccountDetails getUpdateAccountDetails(){
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
                "HomeLoan",1000.0,
                 new Date(System.currentTimeMillis()),
                10.0,
                2);
    }
}
