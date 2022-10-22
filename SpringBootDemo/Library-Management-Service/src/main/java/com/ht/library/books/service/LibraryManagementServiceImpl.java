package com.ht.library.books.service;

import com.ht.library.books.entity.Book;
import com.ht.library.books.handlers.DataNotFoundException;
import com.ht.library.books.model.LibraryBooksResponse;
import com.ht.library.books.repository.LibraryManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ht.library.books.util.LibraryAppConstants.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class LibraryManagementServiceImpl implements ILibraryManagementService {

    @Autowired
    private LibraryManagementRepository libraryManagementRepository;

    @Override
    public Book createBook(Book book) {
        book.setStatus(validateAndUpdateBookStatus(book.getHasBorrowed()));
        var persistedBook = libraryManagementRepository.save(book);
        //created message
        return persistedBook;
    }

    @Override
    public Book updateBookById(Book book, Integer bookId) {
        var existedBook = validateAndGetBook(bookId);
        mapRequestPayloadToEntity(existedBook,book);
        var persistedBook = libraryManagementRepository.saveAndFlush(existedBook);
        //updated message
        return persistedBook;
    }


    @Override
    public Book getBookById(Integer bookId) {
        return validateAndGetBook(bookId);
    }

    @Override
    public LibraryBooksResponse getAllLibraryBooks() {
        var libraryBooks = libraryManagementRepository.findAll();

        var booksAvailability = libraryBooks.stream()
                    .collect(groupingBy(Book::getStatus,counting()));

        var borrowedBooks = String.format(BORROWED_BOOKS_STATUS,booksAvailability.getOrDefault(BORROWED,0L) );
        var availableBooks = String.format(AVAILABLE_BOOKS_STATUS,booksAvailability.getOrDefault(AVAILABLE,0L));

        return new LibraryBooksResponse(libraryBooks,borrowedBooks,availableBooks);
    }

    @Override
    public String removeBookById(Integer bookId) {
        libraryManagementRepository.delete(validateAndGetBook(bookId));
        return BOOK_DELETED;
    }

    @Override
    public String updateBookAvailabilityStatus(Integer bookId) {
        return null;
    }



    private void mapRequestPayloadToEntity(Book existedBook, Book book) {
        existedBook.setBookName(book.getBookName());
        existedBook.setAuthor(book.getAuthor());
        existedBook.setPrice(book.getPrice());
        existedBook.setGenre(book.getGenre());
        existedBook.setHasBorrowed(book.getHasBorrowed());
        existedBook.setStatus(validateAndUpdateBookStatus(book.getHasBorrowed()));
    }

    private Book validateAndGetBook(Integer bookId) {
        return libraryManagementRepository.findById(bookId)
               .orElseThrow(() -> new DataNotFoundException("No Data Found", 404));
    }


    private String validateAndUpdateBookStatus(Boolean hasBorrowed) {
        return hasBorrowed ? BORROWED : AVAILABLE;
    }

}
