package com.vrk.bank.portal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRegistration {

    @Id
    @GeneratedValue
    private Integer registrationId;
    private String accountNumber;
    private String fullName;
    private String userName;
    private String password;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String accountType;
    private String panCardNumber;
    private String contactNumber;
    private String address;
    private String state;
    private String country;
}
