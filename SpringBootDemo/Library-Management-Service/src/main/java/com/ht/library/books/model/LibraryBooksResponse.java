package com.ht.library.books.model;

import com.ht.library.books.entity.Book;
import java.util.List;

public class LibraryBooksResponse {

    private List<Book> libraryBooks;
    private int totalLibraryBooks;
    private long borrowedBooksInfo;
    private long availableBooksInfo;

    public LibraryBooksResponse() {
    }

    public LibraryBooksResponse(List<Book> libraryBooks, int totalLibraryBooks, long borrowedBooksInfo, long availableBooksInfo) {
        this.libraryBooks = libraryBooks;
        this.totalLibraryBooks = totalLibraryBooks;
        this.borrowedBooksInfo = borrowedBooksInfo;
        this.availableBooksInfo = availableBooksInfo;
    }

    public List<Book> getLibraryBooks() {
        return libraryBooks;
    }

    public int getTotalLibraryBooks() {
        return totalLibraryBooks;
    }

    public long getBorrowedBooksInfo() {
        return borrowedBooksInfo;
    }

    public long getAvailableBooksInfo() {
        return availableBooksInfo;
    }
}
