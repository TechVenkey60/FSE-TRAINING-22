package com.vrk.tms.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrk.tms.service.entity.UserRegistration;
import com.vrk.tms.service.model.NewUserData;
import com.vrk.tms.service.model.SignIn;
import com.vrk.tms.service.model.TransactionInput;
import com.vrk.tms.service.model.UpdateAccountDetails;
import com.vrk.tms.service.service.ITransactionBankService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static com.vrk.tms.service.service.TransactionBankUserServiceImpl.AMOUNT_HAS_BEEN_TRANSFERRED_SUCCESSFULLY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ITransactionBankService bankServiceImpl;

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
    void saveUserTransactionTest() {
        when(bankServiceImpl.validateAnsSaveTransaction(any()))
                .thenReturn(AMOUNT_HAS_BEEN_TRANSFERRED_SUCCESSFULLY);

        mockMvc.perform(post("/save/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getTransactionInput())))
                .andExpect(status().isOk());
    }


    @SneakyThrows
    @Test
    void getTransactionDetailsTest() {
        when(bankServiceImpl.fetchUserTransactions(any(),any()))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/user/{accountNumber}","121121")
                .queryParam("sortBy","ASC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @SneakyThrows
    @Test
    void getRegisteredUserDetailsTest() {
        when(bankServiceImpl.fetchUserInfo(any()))
                .thenReturn(getRegisteredUserDetails());

        mockMvc.perform(get("/registered/{accountNumber}","121121")
                .contentType(MediaType.APPLICATION_JSON))
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

    private TransactionInput getTransactionInput(){
        return new TransactionInput("12121",2000.0,"debit",200.0,"212121",new Date());
    }
}
