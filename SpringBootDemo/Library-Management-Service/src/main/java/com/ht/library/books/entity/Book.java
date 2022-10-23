package com.ht.library.books.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer bookId;
    private String bookName;
    private String author;
    private String genre;
    private Double price;
    private Boolean isBookBorrowed;
    private String status;

    public Book() {
    }

    public Book(Integer bookId, String bookName, String author, String genre, Double price, Boolean isBookBorrowed) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.isBookBorrowed = isBookBorrowed;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsBookBorrowed() {
        return isBookBorrowed;
    }

    public void setIsBookBorrowed(Boolean isBookBorrowed) {
        this.isBookBorrowed = isBookBorrowed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
