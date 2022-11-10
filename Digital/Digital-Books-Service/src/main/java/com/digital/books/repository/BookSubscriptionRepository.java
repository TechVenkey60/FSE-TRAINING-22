package com.digital.books.repository;

import com.digital.books.entity.SubscribeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSubscriptionRepository extends JpaRepository<SubscribeBook, Integer> {
}
