package com.digital.books.repository;

import com.digital.books.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT user FROM User user WHERE user.userId =:userId")
    User loadByUserId(@Param("userId") Integer userId);
}
