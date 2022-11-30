package com.vrk.tms.service.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue
    private Integer transactionId;
    private String accountNumber;
    private Double amount;
    private String trxType;
    private Double trxAmount;
    private String receiverAccountNumber;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String status;
}
