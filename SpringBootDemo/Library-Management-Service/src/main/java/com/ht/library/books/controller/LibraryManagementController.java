package com.ht.library.books.controller;

import com.ht.library.books.entity.Book;
import com.ht.library.books.model.BookStatusUpdate;
import com.ht.library.books.model.LibraryBooksResponse;
import com.ht.library.books.service.ILibraryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.ht.library.books.util.ValidationUtil.validateBookStatuChange;
import static com.ht.library.books.util.ValidationUtil.validateInput;

@RestController
public class LibraryManagementController {

    @Autowired
    private ILibraryManagementService libraryManagementService;

    @PostMapping("/add/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {

        validateInput(book);

        var savedBook = libraryManagementService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }


    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable @NotBlank(message = "BookId must not be blank") @NotNull(message = "BookId must not be null") Integer bookId,
                                           @RequestBody Book book) {
        validateInput(book);
        return new ResponseEntity<>(libraryManagementService.updateBookById(book, bookId), HttpStatus.OK);
    }

    @GetMapping("/read/{bookId}")
    public ResponseEntity<Book> fetchBookById(@PathVariable @NotBlank(message = "BookId must not be blank") @NotNull(message = "BookId must not be null") Integer bookId) {
        //validation
        return new ResponseEntity<>(libraryManagementService.getBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<LibraryBooksResponse> getAllBooks() {
        return new ResponseEntity<>(libraryManagementService.getAllLibraryBooks(), HttpStatus.OK);
    }

    @PutMapping("/change/{bookId}")
    public ResponseEntity<String> updateBookStatus(@PathVariable @NotBlank(message = "BookId must not be blank") @NotNull(message = "BookId must not be null") Integer bookId,
                                                   @RequestBody BookStatusUpdate bookStatusUpdate) {

        validateBookStatuChange(bookStatusUpdate);
        return new ResponseEntity<>(libraryManagementService.updateBookAvailabilityStatus(bookStatusUpdate, bookId), HttpStatus.OK);
    }

}
