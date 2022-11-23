package com.vrk.bank.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserLoanDto {
    private String accountNumber;
    private String loanType;
    private Double loanAmount;
    private Date loanAppliedDate;
    private Double roi;
    private Integer durationOfLoan;
}
