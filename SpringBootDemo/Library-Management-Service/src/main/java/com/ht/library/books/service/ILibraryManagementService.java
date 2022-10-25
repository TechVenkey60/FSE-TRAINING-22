package com.ht.library.books.service;

import com.ht.library.books.entity.Book;
import com.ht.library.books.model.BookStatusUpdate;
import com.ht.library.books.model.LibraryBooksResponse;

public interface ILibraryManagementService {

    Book createBook(Book book);
    Book updateBookById(Book book,Integer bookId);
    Book getBookById(Integer bookId);
    LibraryBooksResponse getAllLibraryBooks();
    String removeBookById(Integer bookId);
    Book updateBookAvailabilityStatus(BookStatusUpdate bookStatusUpdate,Integer bookId);
}
