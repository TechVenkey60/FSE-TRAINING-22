package com.digital.books.repository;

import com.digital.books.entity.DigitalBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalBooksRepository extends JpaRepository<DigitalBook, Integer> {

    @Query("SELECT book FROM DigitalBook book WHERE book.bookId = :bookId AND book.authorId =:authorId")
    DigitalBook findBookByIdAndAuthorId(@Param("bookId") Integer bookId, @Param("authorId") Integer authorId);

    @Query("SELECT book FROM DigitalBook book WHERE book.author = :author AND book.title =:title AND book.publisher =:publisher AND book.price =:price")
    DigitalBook searchBook(@Param("category") String category,
                           @Param("title") String title,
                           @Param("author") String author,
                           @Param("publisher") String publisher,
                           @Param("price") Double price);
}
