package com.digital.books.service;

import com.digital.books.entity.DigitalBook;
import com.digital.books.entity.ReaderSubscribedBooks;
import com.digital.books.model.SubscriptionBook;
import java.util.List;

public interface IDigitalBookService {

    DigitalBook createDigitalBook(DigitalBook digitalBook, Integer authorId);
    DigitalBook updateDigitalBook(DigitalBook digitalBook,Integer authorId,Integer bookId);
    String updateBookVisibility(Integer authorId,Integer bookId,String block);
    DigitalBook fetchBooks(String category, String title, String author, String publisher, Double price);
    String subScribeDigitalBook(Integer bookId, SubscriptionBook subscriptionBook);

    List<ReaderSubscribedBooks> getReadersBook(String emailId);

    ReaderSubscribedBooks fetchBookBySubIdAndEmailId(String emailId, Integer subscriptionId);

    String cancelBookSubscription(String emailId, Integer subscriptionId);
}
