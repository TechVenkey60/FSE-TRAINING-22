package com.digital.books.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "DIGITAL_BOOKS")
public class DigitalBook {

    @Id
    @GeneratedValue
    private Integer bookId;
    private Integer authorId;
    @NotNull(message = "Logo url must not be empty/blank. Please provide proper input.")
    @NotBlank(message = "Logo url must not be empty/blank. Please provide proper input.")
    private String logo;
    @NotNull(message = "Title must not be empty/blank. Please provide proper input.")
    @NotBlank(message = "Title must not be empty/blank. Please provide proper input.")
    private String title;
    private String category;
    @NotNull(message = "Author must not be empty/blank. Please provide proper input.")
    @NotBlank(message = "Author must not be empty/blank. Please provide proper input.")
    private String author;
    private Double price;
    private String publisher;
    @Temporal(TemporalType.DATE)
    private Date publishedDate;
    private String chapter;
    private Boolean active;
    private String block;

    public DigitalBook() {
    }

    public DigitalBook(Integer bookId, Integer authorId,String logo, String title, String category, String author, Double price, String publisher, Date publishedDate, String chapter, Boolean active, String block) {
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
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
