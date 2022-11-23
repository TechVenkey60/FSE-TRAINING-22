package com.vrk.bank.portal.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class LoanDetails {

    @Id
    @GeneratedValue
    private Integer loadId;
    private String accountNumber;
    private String loanType;
    private Double loanAmount;
    @Temporal(TemporalType.DATE)
    private Date loanAppliedDate;
    private Double roi;
    private Integer durationOfLoan;
}
