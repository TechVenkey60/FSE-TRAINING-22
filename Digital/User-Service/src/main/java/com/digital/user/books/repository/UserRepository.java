package com.digital.user.books.repository;

import com.digital.user.books.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT user FROM User user WHERE user.email =:email")
    User loadByEmail(@Param("email") String email);

    @Query("SELECT user FROM User user WHERE user.userName =:userName")
    User loadUserByUserName(@Param("userName") String userName);
}
