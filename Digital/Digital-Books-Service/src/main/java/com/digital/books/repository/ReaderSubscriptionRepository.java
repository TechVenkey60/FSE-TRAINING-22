package com.digital.books.repository;

import com.digital.books.entity.ReaderSubscribedBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderSubscriptionRepository extends JpaRepository<ReaderSubscribedBooks, Integer> {
    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.block =:block AND book.status =:status")
    List<ReaderSubscribedBooks> findReaderBookByEmailId(@Param("emailId") String emailId, @Param("block") String block,@Param("status") String status);

    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.subscriptionId =:subscriptionId AND book.block =:block")
    ReaderSubscribedBooks findReaderBookByEmailIdAndSubId(@Param("emailId") String emailId, @Param("subscriptionId") Integer subscriptionId,@Param("block") String block);

    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.subscriptionId =:subscriptionId")
    ReaderSubscribedBooks loadReaderBookByEmailIdAndSubId(@Param("emailId") String emailId, @Param("subscriptionId") Integer subscriptionId);

    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.emailId =:emailId AND book.status =:status")
    List<ReaderSubscribedBooks> getOnlySubscribedBooks(@Param("emailId") String emailId, @Param("status") String status);

    @Query("SELECT book FROM ReaderSubscribedBooks book WHERE book.subscriptionId =:subscriptionId")
    ReaderSubscribedBooks findBySubscriptonId(@Param("subscriptionId") Integer subscriptionId);
}
