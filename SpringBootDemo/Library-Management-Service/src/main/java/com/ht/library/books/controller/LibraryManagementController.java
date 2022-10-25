package com.ht.library.books.controller;

import com.ht.library.books.entity.Book;
import com.ht.library.books.handlers.InvalidDataException;
import com.ht.library.books.model.BookStatusUpdate;
import com.ht.library.books.model.LibraryBooksResponse;
import com.ht.library.books.service.ILibraryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ht.library.books.util.LibraryAppConstants.PLEASE_PROVIDE_EITHER_TRUE_OR_FALSE_VALUE_ONLY;
import static com.ht.library.books.util.ValidationUtil.validateBookStatusChange;
import static com.ht.library.books.util.ValidationUtil.validateInput;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
public class LibraryManagementController {

    @Autowired
    private ILibraryManagementService libraryManagementService;

    @PostMapping("/add/book")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {

        validateInput(book);

        var savedBook = libraryManagementService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.OK);
    }


    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId,
                                           @RequestBody Book book) {
        validateInput(book);
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

    @PutMapping("/borrow/{bookId}")
    public ResponseEntity<Book> updateBookStatus(@PathVariable Integer bookId,
                                                   @RequestBody BookStatusUpdate bookStatusUpdate) {

        if(!isBlank(bookStatusUpdate.getIsBookBorrowed())){
            validateBookStatusChange(bookStatusUpdate.getIsBookBorrowed());
        }else {
            throw new InvalidDataException(PLEASE_PROVIDE_EITHER_TRUE_OR_FALSE_VALUE_ONLY);
        }

        return new ResponseEntity<>(libraryManagementService.updateBookAvailabilityStatus(bookStatusUpdate, bookId), HttpStatus.OK);
    }


    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer bookId){
        return new ResponseEntity<>(libraryManagementService.removeBookById(bookId),HttpStatus.OK);
    }
}
