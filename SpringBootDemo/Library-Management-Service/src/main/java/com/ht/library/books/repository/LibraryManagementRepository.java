package com.ht.library.books.repository;

import com.ht.library.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryManagementRepository extends JpaRepository<Book,Integer> {
}
