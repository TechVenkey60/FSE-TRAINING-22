package com.vrk.bank.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateAccountDetails {
    private String accountNumber;
    private String fullName;
    private String password;
    private String address;
    private String state;
    private String country;
    private String contactNumber;
}
