package com.vrk.tms.service.repository;

import com.vrk.tms.service.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Integer> {
    @Query("SELECT transaction FROM TransactionDetails transaction WHERE transaction.accountNumber = :accountNumber ORDER BY transaction.date ASC")
    List<TransactionDetails> loadTransactionDetailsByAccountNumberInASCOrder(@Param("accountNumber") String accountNumber);

    @Query("SELECT transaction FROM TransactionDetails transaction WHERE transaction.accountNumber = :accountNumber ORDER BY transaction.date DESC")
    List<TransactionDetails> loadTransactionDetailsByAccountNumberInDESCOrder(@Param("accountNumber") String accountNumber);
}
