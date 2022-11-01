package com.digital.books.repository;

import com.digital.books.entity.ReaderSubscribedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReaderSubscriptionRepository extends JpaRepository<ReaderSubscribedBooks, Integer> {
    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.block =:block")
    List<ReaderSubscribedBooks> findReaderBookByEmailId(@Param("emailId") String emailId, @Param("block") String block);

    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.subscriptionId =:subscriptionId AND book.block =:block")
    ReaderSubscribedBooks findReaderBookByEmailIdAndSubId(@Param("emailId") String emailId, @Param("subscriptionId") Integer subscriptionId, @Param("block") String block);

}
