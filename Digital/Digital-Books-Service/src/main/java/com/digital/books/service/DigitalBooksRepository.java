package com.digital.books.service;

import com.digital.books.model.DigitalBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBooksRepository extends JpaRepository<DigitalBook,Integer> {

    @Query("SELECT book FROM DigitalBook book WHERE book.bookId = :bookId AND book.authorId =:authorId")
    DigitalBook findBookByIdAndAuthorId(@Param("bookId") Integer bookId, @Param("authorId") Integer authorId);

}
