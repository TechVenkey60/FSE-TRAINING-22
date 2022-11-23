package com.vrk.bank.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateAccountDetails {
    private String accountNumber;
    private String fullName;
    private String password;
    private String address;
    private String state;
    private String email;
    private String country;
    private String contactNumber;
}
