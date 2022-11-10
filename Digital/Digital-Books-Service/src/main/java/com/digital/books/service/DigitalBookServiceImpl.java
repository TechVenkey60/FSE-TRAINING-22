package com.digital.books.service;

import com.digital.books.entity.DigitalBook;
import com.digital.books.entity.ReaderSubscribedBooks;
import com.digital.books.entity.SubscribeBook;
import com.digital.books.handlers.DataNotFoundException;
import com.digital.books.model.SubscriptionBook;
import com.digital.books.repository.BookSubscriptionRepository;
import com.digital.books.repository.DigitalBooksRepository;
import com.digital.books.repository.ReaderSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

@Service
public class DigitalBookServiceImpl implements IDigitalBookService {

    private static final String YES = "yes";
    private static final String NO = "no";

    @Autowired
    private DigitalBooksRepository digitalBooksRepository;

    @Autowired
    private BookSubscriptionRepository subscriptionRepository;

    @Autowired
    private ReaderSubscriptionRepository readerSubscriptionRepository;


    @Override
    public List<DigitalBook> createDigitalBook(DigitalBook digitalBook,
                                               Integer authorId) {

        digitalBook.setAuthorId(authorId);
        digitalBooksRepository.save(digitalBook);
        var books = digitalBooksRepository.findBooksByAuthorId(authorId);
        return books;
    }

    @Transactional
    @Override
    public List<DigitalBook> updateDigitalBook(DigitalBook digitalBook,
                                         Integer authorId,
                                         Integer bookId) {
        System.out.println(String.format("Entered updateDigitalBook() method with DigitalBook Id: and authorId: %d", bookId, authorId));

        var existedBook = validateAuthorIdAndBookId(authorId, bookId);
        updateDigitalBookEntity(digitalBook, existedBook);
        digitalBooksRepository.saveAndFlush(existedBook);
        System.out.println(String.format("Saved  DigitalBook with Id: %d and authorId: %d", bookId, authorId));
        var books = digitalBooksRepository.findBooksByAuthorId(authorId);
        return books;
    }

    @Override
    public List<DigitalBook> updateBookVisibility(Integer authorId, Integer bookId, String block) {
        System.out.println(String.format("Entered updateBookVisibility() method with DigitalBook Id: %d and authorId: %d", bookId, authorId));
        var existedBook = validateAuthorIdAndBookId(authorId, bookId);
        existedBook.setBlock(block);
        digitalBooksRepository.saveAndFlush(existedBook);

        var books = digitalBooksRepository.findBooksByAuthorId(authorId);
        return books;
    }

    @Override
    public DigitalBook fetchBooks(String category,
                                  String title,
                                  String author,
                                  String publisher,
                                  Double price) {
        var digitalBooks = digitalBooksRepository.searchBook(category, title, author, publisher, price,"no");
        if (Objects.isNull(digitalBooks)) {
            throw new DataNotFoundException("Book is not available or blocked by author.", 404);
        }
        return digitalBooks;
    }

    @Override
    public ReaderSubscribedBooks subScribeDigitalBook(Integer bookId,
                                       SubscriptionBook subscriptionBook) {
        var persistedBook = digitalBooksRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("No Book Information Found", 404));

        var subscribeBook = new SubscribeBook();
        subscribeBook.setBookId(subscriptionBook.getBookId());
        subscribeBook.setEmailId(subscriptionBook.getEmailId());
        subscribeBook.setStatus("subscribed");

        var subscribedBook = subscriptionRepository.save(subscribeBook);

        ReaderSubscribedBooks readerBook = new ReaderSubscribedBooks();

        mapDataToReaderEntity(persistedBook, subscribedBook, readerBook);

        var readerSubscribeBook = readerSubscriptionRepository.save(readerBook);

        return readerSubscribeBook;
    }

    @Override
    public List<ReaderSubscribedBooks> getReadersBook(String emailId) {
        var response = readerSubscriptionRepository.findReaderBookByEmailId(emailId, NO,"subscribed");
        if (response.isEmpty()) {
            throw new DataNotFoundException("Not Data Found", 404);
        }
        return response;
    }

    @Override
    public ReaderSubscribedBooks fetchBookBySubIdAndEmailId(String emailId,
                                                            Integer subscriptionId) {

        var response = readerSubscriptionRepository.findReaderBookByEmailIdAndSubId(emailId, subscriptionId, NO);

        if (response == null || isNull(response.getEmailId())) {
            throw new DataNotFoundException("Not Data Found", 404);
        }
        return response;
    }

    @Override
    public List<ReaderSubscribedBooks> cancelBookSubscription(String emailId, Integer subscriptionId) {
         var readerBook = readerSubscriptionRepository.loadReaderBookByEmailIdAndSubId(emailId, subscriptionId);
         readerBook.setStatus("unsubscribed");
         readerSubscriptionRepository.save(readerBook);
         var allSubscribedBooks = readerSubscriptionRepository.getOnlySubscribedBooks(emailId,"subscribed");
         return allSubscribedBooks;
    }

    @Override
    public ReaderSubscribedBooks fetchReaderSubscribedBook(Integer subscriptionId) {

        var readerBook = readerSubscriptionRepository.findBySubscriptonId(subscriptionId);
        if(isNull(readerBook)){
            throw new DataNotFoundException("Book Content is not available", 404);
        }
        return readerBook;
    }

    private void mapDataToReaderEntity(DigitalBook persistedBook, SubscribeBook subscribedBook, ReaderSubscribedBooks readerBook) {

        readerBook.setBookId(persistedBook.getBookId());
        readerBook.setTitle(persistedBook.getTitle());
        readerBook.setLogo(persistedBook.getLogo());
        readerBook.setAuthor(persistedBook.getAuthor());
        readerBook.setAuthorId(persistedBook.getAuthorId());
        readerBook.setPrice(persistedBook.getPrice());
        readerBook.setCategory(persistedBook.getCategory());
        readerBook.setPublisher(persistedBook.getPublisher());
        readerBook.setPublishedDate(persistedBook.getPublishedDate());
        readerBook.setChapter(persistedBook.getChapter());
        readerBook.setActive(persistedBook.getActive());
        readerBook.setBlock(persistedBook.getBlock());

        readerBook.setSubscriptionId(subscribedBook.getSubscriptionId());
        readerBook.setEmailId(subscribedBook.getEmailId());
        readerBook.setStatus(subscribedBook.getStatus());
        readerBook.setSubscribedDate(subscribedBook.getSubscribedDate());

    }

    private DigitalBook validateAuthorIdAndBookId(Integer authorId, Integer bookId) {
        var existedBook = digitalBooksRepository.findBookByIdAndAuthorId(bookId, authorId);

        if (isNull(existedBook)) {
            System.out.println(String.format("Not Data Found for authorId: %d and bookId: %d", authorId, bookId));
            throw new DataNotFoundException(String.format("Not Data Found for authorId: %d and bookId: %d", authorId, bookId), 404);
        }
        return existedBook;
    }

    private void updateDigitalBookEntity(DigitalBook digitalBook, DigitalBook existedBook) {

        existedBook.setTitle(digitalBook.getTitle());
        existedBook.setAuthor(digitalBook.getAuthor());
        existedBook.setPrice(digitalBook.getPrice());
        existedBook.setPublisher(digitalBook.getPublisher());
        existedBook.setActive(digitalBook.getActive());
        existedBook.setCategory(digitalBook.getCategory());
        existedBook.setChapter(digitalBook.getChapter());
        existedBook.setPublishedDate(digitalBook.getPublishedDate());
    }
}
