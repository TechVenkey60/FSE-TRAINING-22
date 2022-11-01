package com.digital.books.entity;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class SubscribeBook {

    private Integer subscriptionId;
    private String emailId;
    private Integer bookId;
    @Temporal(TemporalType.DATE)
    private Date subscribedDate;
    private String status;

    public SubscribeBook() {
    }

    public SubscribeBook(Integer subscriptionId, String emailId, Integer bookId, Date subscribedDate, String status) {
        this.subscriptionId = subscriptionId;
        this.emailId = emailId;
        this.bookId = bookId;
        this.subscribedDate = subscribedDate;
        this.status = status;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getSubscribedDate() {
        return subscribedDate;
    }

    public void setSubscribedDate(Date subscribedDate) {
        this.subscribedDate = subscribedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
