package com.vrk.bank.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewUserData {

    private String fullName;
    private String userName;
    private String password;
    private String email;
    private Date dob;
    private String accountType;
    private String panCardNumber;
    private String contactNumber;
    private String address;
    private String state;
    private String country;
}
