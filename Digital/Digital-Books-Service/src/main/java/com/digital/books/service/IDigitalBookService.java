package com.digital.books.service;

import com.digital.books.model.DigitalBook;

import java.util.List;

public interface IDigitalBookService {

    DigitalBook createDigitalBook(DigitalBook digitalBook,Integer authorId);
    DigitalBook updateDigitalBook(DigitalBook digitalBook,Integer authorId,Integer bookId);
    String updateBookVisibility(Integer authorId,Integer bookId,String block);

    List<DigitalBook> fetchBooks(String category, String title, String author, String publisher, Double price);
}
