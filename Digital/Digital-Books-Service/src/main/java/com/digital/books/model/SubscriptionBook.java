package com.digital.books.model;

public class SubscriptionBook {
    private Integer bookId;
    private String emailId;
    private String status;

    public SubscriptionBook() {
    }

    public SubscriptionBook(Integer bookId, String emailId,String status) {
        this.bookId = bookId;
        this.emailId = emailId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
