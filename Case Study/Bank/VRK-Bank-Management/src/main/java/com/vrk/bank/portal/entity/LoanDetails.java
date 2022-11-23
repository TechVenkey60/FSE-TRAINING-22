package com.vrk.bank.portal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LOAN_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    private Long emi;
}
