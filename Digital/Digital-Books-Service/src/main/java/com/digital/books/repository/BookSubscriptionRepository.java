package com.digital.books.repository;

import com.digital.books.entity.SubscribeBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSubscriptionRepository extends JpaRepository<SubscribeBook, Integer> {
}
