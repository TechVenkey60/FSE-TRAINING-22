package com.ht.library.books.model;

import com.ht.library.books.entity.Book;
import java.util.List;

public class LibraryBooksResponse {

    private List<Book> libraryBooks;
    private String borrowedBooksInfo;
    private String availableBooksInfo;

    public LibraryBooksResponse() {
    }

    public LibraryBooksResponse(List<Book> libraryBooks, String borrowedBooksInfo, String availableBooksInfo) {
        this.libraryBooks = libraryBooks;
        this.borrowedBooksInfo = borrowedBooksInfo;
        this.availableBooksInfo = availableBooksInfo;
    }

    public List<Book> getLibraryBooks() {
        return libraryBooks;
    }

    public void setLibraryBooks(List<Book> libraryBooks) {
        this.libraryBooks = libraryBooks;
    }

    public String getBorrowedBooksInfo() {
        return borrowedBooksInfo;
    }

    public void setBorrowedBooksInfo(String borrowedBooksInfo) {
        this.borrowedBooksInfo = borrowedBooksInfo;
    }

    public String getAvailableBooksInfo() {
        return availableBooksInfo;
    }

    public void setAvailableBooksInfo(String availableBooksInfo) {
        this.availableBooksInfo = availableBooksInfo;
    }
}
