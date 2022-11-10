package com.digital.books.service;

import com.digital.books.entity.DigitalBook;
import com.digital.books.entity.ReaderSubscribedBooks;
import com.digital.books.model.SubscriptionBook;
import java.util.List;

public interface IDigitalBookService {

    List<DigitalBook> createDigitalBook(DigitalBook digitalBook, Integer authorId);
    List<DigitalBook> updateDigitalBook(DigitalBook digitalBook,Integer authorId,Integer bookId);
    List<DigitalBook> updateBookVisibility(Integer authorId,Integer bookId,String block);
    DigitalBook fetchBooks(String category, String title, String author, String publisher, Double price);
    ReaderSubscribedBooks subScribeDigitalBook(Integer bookId, SubscriptionBook subscriptionBook);

    List<ReaderSubscribedBooks> getReadersBook(String emailId);

    ReaderSubscribedBooks fetchBookBySubIdAndEmailId(String emailId, Integer subscriptionId);

    List<ReaderSubscribedBooks> cancelBookSubscription(String emailId, Integer subscriptionId);

    ReaderSubscribedBooks fetchReaderSubscribedBook(Integer subscriptionId);
}
