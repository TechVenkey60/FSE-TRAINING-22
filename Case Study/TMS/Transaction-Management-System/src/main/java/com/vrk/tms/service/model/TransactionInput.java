package com.vrk.tms.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionInput {

    private String accountNumber;
    private Double amount;
    private String trxType;
    private Double trxAmount;
    private String receiverAccountNumber;
    private Date date;
}
