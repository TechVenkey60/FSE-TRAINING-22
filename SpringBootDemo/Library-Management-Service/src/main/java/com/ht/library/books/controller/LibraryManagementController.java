package com.ht.library.books.controller;

import com.ht.library.books.entity.Book;
import com.ht.library.books.model.LibraryBooksResponse;
import com.ht.library.books.service.ILibraryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryManagementController {

    @Autowired
    private ILibraryManagementService libraryManagementService;

    @PostMapping("/add/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {

        var savedBook = libraryManagementService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }


    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId,
                                           @RequestBody Book book) {
        //validation
        return new ResponseEntity<>(libraryManagementService.updateBookById(book, bookId), HttpStatus.OK);
    }

    @GetMapping("/read/{bookId}")
    public ResponseEntity<Book> fetchBookById(@PathVariable Integer bookId) {
        //validation
        return new ResponseEntity<>(libraryManagementService.getBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<LibraryBooksResponse> getAllBooks() {
        return new ResponseEntity<>(libraryManagementService.getAllLibraryBooks(), HttpStatus.OK);
    }

}
