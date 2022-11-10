package com.digital.books.model;

public class SubscriptionBook {
    private Integer bookId;
    private String emailId;

    public SubscriptionBook() {
    }

    public SubscriptionBook(Integer bookId, String emailId) {
        this.bookId = bookId;
        this.emailId = emailId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
