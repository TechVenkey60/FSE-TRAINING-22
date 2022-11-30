package com.vrk.tms.service.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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
    private Double balance;
}
