package com.ht.library.books.service;

import com.ht.library.books.entity.Book;
import com.ht.library.books.handlers.DataNotFoundException;
import com.ht.library.books.model.BookStatusUpdate;
import com.ht.library.books.model.LibraryBooksResponse;
import com.ht.library.books.repository.LibraryManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

import static com.ht.library.books.util.LibraryAppConstants.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.setField;

@Service
public class LibraryManagementServiceImpl implements ILibraryManagementService {

    @Autowired
    private LibraryManagementRepository libraryManagementRepository;

    @Transactional
    @Override
    public Book createBook(Book book) {
        book.setStatus(validateAndUpdateBookStatus(book.getIsBookBorrowed()));
        var persistedBook = libraryManagementRepository.save(book);
        //created message
        return persistedBook;
    }

    @Transactional
    @Override
    public Book updateBookById(Book book, Integer bookId) {
        var existedBook = validateAndGetBook(bookId);
        mapRequestPayloadToEntity(existedBook, book);
        var persistedBook = libraryManagementRepository.saveAndFlush(existedBook);
        //updated message
        return persistedBook;
    }


    @Transactional
    @Override
    public Book getBookById(Integer bookId) {
        return validateAndGetBook(bookId);
    }

    @Override
    public LibraryBooksResponse getAllLibraryBooks() {
        var libraryBooks = libraryManagementRepository.findAll();

        var booksAvailability = libraryBooks.stream()
                .collect(groupingBy(Book::getStatus, counting()));

        var borrowedBooks = booksAvailability.getOrDefault(BORROWED, 0L);
        var availableBooks = booksAvailability.getOrDefault(AVAILABLE, 0L);

        return new LibraryBooksResponse(libraryBooks, libraryBooks.size(), borrowedBooks, availableBooks);
    }

    @Transactional
    @Override
    public String removeBookById(Integer bookId) {
        libraryManagementRepository.delete(validateAndGetBook(bookId));
        return BOOK_DELETED;
    }

    @Transactional
    @Override
    public Book updateBookAvailabilityStatus(BookStatusUpdate bookStatusUpdate,
                                             Integer bookId) {
        Book book = validateAndGetBook(bookId);
        book.setIsBookBorrowed(Boolean.parseBoolean(bookStatusUpdate.getIsBookBorrowed()));
        String bookStatus = validateAndUpdateBookStatus(Boolean.parseBoolean(bookStatusUpdate.getIsBookBorrowed()));
        book.setStatus(bookStatus);
        var updatedBook = libraryManagementRepository.save(book);
        System.out.println(String.format("%s Book is %s", book.getBookName(), bookStatus));
        return updatedBook;
    }

    @Override
    public Book validateAndUpdateBookById(Map<String, Object> fields, Integer bookId) {

        Optional<Book> existingBook = libraryManagementRepository.findById(bookId);

        if (existingBook.isPresent()) {
            fields.forEach((key, value) -> {
                var field = findField(Book.class, key);
                field.setAccessible(true);
                setField(field, existingBook.get(), value);
            });
            return libraryManagementRepository.saveAndFlush(existingBook.get());
        }
        return null;
    }


    private void mapRequestPayloadToEntity(Book existedBook, Book book) {
        existedBook.setBookName(book.getBookName());
        existedBook.setAuthor(book.getAuthor());
        existedBook.setPrice(book.getPrice());
        existedBook.setGenre(book.getGenre());
        existedBook.setIsBookBorrowed(book.getIsBookBorrowed());
        existedBook.setStatus(validateAndUpdateBookStatus(book.getIsBookBorrowed()));
    }

    private Book validateAndGetBook(Integer bookId) {
        return libraryManagementRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("No Data Found", 404));
    }


    private String validateAndUpdateBookStatus(Boolean hasBorrowed) {
        return hasBorrowed ? BORROWED : AVAILABLE;
    }

}
