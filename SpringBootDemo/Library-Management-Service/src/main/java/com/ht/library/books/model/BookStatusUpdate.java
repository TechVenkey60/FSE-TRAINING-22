package com.ht.library.books.model;

public class BookStatusUpdate {
    private String isBookBorrowed;

    public BookStatusUpdate() {
    }

    public BookStatusUpdate(String isBookBorrowed) {
        this.isBookBorrowed = isBookBorrowed;
    }

    public String getIsBookBorrowed() {
        return isBookBorrowed;
    }
}
