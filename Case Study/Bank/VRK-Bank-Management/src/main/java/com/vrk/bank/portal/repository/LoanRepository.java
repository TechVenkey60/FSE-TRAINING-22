package com.vrk.bank.portal.repository;

import com.vrk.bank.portal.entity.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanDetails, Integer> {
}
