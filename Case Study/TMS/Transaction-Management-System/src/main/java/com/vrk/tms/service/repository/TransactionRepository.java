package com.vrk.tms.service.repository;

import com.vrk.tms.service.entity.TransactionDetails;
import com.vrk.tms.service.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails,Integer> {
    @Query("SELECT user FROM UserRegistration user WHERE user.accountNumber = :receiverAccountNumber")
    UserRegistration getDetailsByAccountNumber(@Param("receiverAccountNumber") String receiverAccountNumber);
}
