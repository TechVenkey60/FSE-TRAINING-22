package com.vrk.bank.portal.repository;

import com.vrk.bank.portal.entity.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanDetails, Integer> {
    @Query("SELECT loan FROM LoanDetails loan WHERE loan.accountNumber = :accountNumber")
    List<LoanDetails> fetchByAccountNumber(@Param("accountNumber") String accountNumber);
}
