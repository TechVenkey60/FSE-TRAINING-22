package com.vrk.tms.service.repository;


import com.vrk.tms.service.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistryRepository extends JpaRepository<UserRegistration, Integer> {

    @Query("SELECT user FROM UserRegistration user WHERE user.userName = :userName OR user.email =:email")
    UserRegistration loadUserByUserNameAndEmail(@Param("userName") String userName, @Param("email") String email);

    @Query("SELECT user FROM UserRegistration user WHERE user.userName = :userName")
    UserRegistration loadUserByUserName(@Param("userName") String userName);

    @Query("SELECT user FROM UserRegistration user WHERE user.accountNumber = :accountNumber")
    UserRegistration getUserByAccountNumber(@Param("accountNumber") String accountNumber);
}
