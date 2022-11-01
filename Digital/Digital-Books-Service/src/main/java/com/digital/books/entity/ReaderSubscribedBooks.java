package com.digital.books.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ReaderSubscribedBooks {

    @Id
    @GeneratedValue
    private Integer readerId;
    private Integer bookId;
    private Integer authorId;
    private String logo;
    private String title;
    private String category;
    private String author;
    private Double price;
    private String publisher;
    @Temporal(TemporalType.DATE)
    private Date publishedDate;
    private String chapter;
    private Boolean active;
    private String block;

    private Integer subscriptionId;
    private String emailId;
    @Temporal(TemporalType.DATE)
    private Date subscribedDate;
    private String status;

    public ReaderSubscribedBooks() {
    }

    public ReaderSubscribedBooks(Integer readerId, Integer bookId, Integer authorId, String logo, String title, String category, String author, Double price, String publisher, Date publishedDate, String chapter, Boolean active, String block, Integer subscriptionId, String emailId, Date subscribedDate, String status) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.authorId = authorId;
        this.logo = logo;
        this.title = title;
        this.category = category;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.chapter = chapter;
        this.active = active;
        this.block = block;
        this.subscriptionId = subscriptionId;
        this.emailId = emailId;
        this.subscribedDate = subscribedDate;
        this.status = status;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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
