package com.digital.books.controller;

import com.digital.books.entity.DigitalBook;
import com.digital.books.entity.ReaderSubscribedBooks;
import com.digital.books.handlers.InvalidDataException;
import com.digital.books.model.SubscriptionBook;
import com.digital.books.repository.DigitalBooksRepository;
import com.digital.books.repository.UserRepository;
import com.digital.books.service.IDigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@CrossOrigin("*")
public class DigitalBooksController {

    @Autowired
    private IDigitalBookService digitalBookService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DigitalBooksRepository digitalBooksRepository;

    @PostMapping("/author/{authorId}/books")
    @CrossOrigin("*")
    public ResponseEntity<List<DigitalBook>> saveDigitalBook(@PathVariable Integer authorId,
                                                             @RequestBody @Valid DigitalBook digitalBook) {

        validateInputData(authorId, "authorId");
        var user = userRepository.loadByUserId(authorId);
        if(Objects.isNull(user) || !"author".equalsIgnoreCase(user.getRole())){
           throw new InvalidDataException("Author Role is required to create a new book");
        }
        var savedDigitalBook = digitalBookService.createDigitalBook(digitalBook, authorId);
        return new ResponseEntity<>(savedDigitalBook, HttpStatus.OK);
    }


    @PutMapping("/author/{authorId}/books/{bookId}")
    @CrossOrigin("*")
    public ResponseEntity<List<DigitalBook>> updateDigitalBook(@PathVariable Integer authorId,
                                                         @PathVariable Integer bookId,
                                                         @RequestBody DigitalBook digitalBook) {
        validateInputData(authorId, "authorId");
        validateInputData(bookId, "bookId");

        var updatedDigitalBook = digitalBookService.updateDigitalBook(digitalBook, authorId, bookId);
        return new ResponseEntity<>(updatedDigitalBook, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}/books/{bookId}")
    @CrossOrigin("*")
    public ResponseEntity<DigitalBook> getBookByAuthorIdAndBookId(@PathVariable Integer authorId,
                                                               @PathVariable Integer bookId) {
//        validateInputData(authorId, "authorId");
//        validateInputData(bookId, "bookId");

        var book = digitalBooksRepository.findBookByIdAndAuthorId(bookId,authorId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/author/{authorId}/book/{bookId}")
    public ResponseEntity<List<DigitalBook>> updateDigitalBooksVisibility(@RequestParam(defaultValue = "no") String block,
                                                               @PathVariable Integer authorId,
                                                               @PathVariable Integer bookId) {

//        //validate input
//        validateInputData(authorId, "authorId");
//        validateInputData(bookId, "bookId");
//        validateBlockValue(block);

        var response = digitalBookService.updateBookVisibility(authorId, bookId, block);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/search")
    public ResponseEntity<DigitalBook> searchBooks(@RequestParam String category,
                                                   @RequestParam String title,
                                                   @RequestParam String author,
                                                   @RequestParam String publisher,
                                                   @RequestParam Double price){

        return  new ResponseEntity<>(digitalBookService.fetchBooks(category,title,author,publisher,price),HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping("/{bookId}/subscribe")
    public ResponseEntity<ReaderSubscribedBooks> subscribeBook(@PathVariable Integer bookId,
                                                               @RequestBody SubscriptionBook subscriptionBook){


        return new ResponseEntity<>(digitalBookService.subScribeDigitalBook(bookId,subscriptionBook),HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/readers/{emailId}/books")
    public ResponseEntity<?> getReaderSubscribedBooks(@PathVariable String emailId){

        return new ResponseEntity<>(digitalBookService.getReadersBook(emailId),HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/readers/{emailId}/books/{subscriptionId}")
    public ResponseEntity<?> getBookBySubIdAndEmailId(@PathVariable String emailId,
                                                      @PathVariable Integer subscriptionId){

        return new ResponseEntity<>(digitalBookService.fetchBookBySubIdAndEmailId(emailId,subscriptionId),HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/readers/{emailId}/books/{subscriptionId}/read")
    public ResponseEntity<?> readBookContentBySubIdAndEmailId(@PathVariable String emailId,
                                                      @PathVariable Integer subscriptionId){

        return new ResponseEntity<>(digitalBookService.fetchBookBySubIdAndEmailId(emailId,subscriptionId),HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/readers/{emailId}/books/{subscriptionId}/cancelSubscription")
    public ResponseEntity<?> removeSubscriptionByEmailIdAndSubId(@PathVariable String emailId,
                                                              @PathVariable Integer subscriptionId){

        return new ResponseEntity<>(digitalBookService.cancelBookSubscription(emailId,subscriptionId),HttpStatus.OK);
    }


    @CrossOrigin("*")
    @GetMapping("/author/{authorId}/books")
    public ResponseEntity<List<DigitalBook>> getAuthorBooks(@PathVariable Integer authorId){

        var user = userRepository.loadByUserId(authorId);
        if(Objects.isNull(user) || !"author".equalsIgnoreCase(user.getRole())){
            throw new InvalidDataException("Author Role is required to create a new book");
        }

        var books = digitalBooksRepository.findBooksByAuthorId(authorId);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }


    @CrossOrigin("*")
    @GetMapping("/books/{subscriptionId}")
    public ResponseEntity<ReaderSubscribedBooks> readSubscribedBookInfo(@PathVariable Integer subscriptionId){
        var readerBook = digitalBookService.fetchReaderSubscribedBook(subscriptionId);
        return  new ResponseEntity<>(readerBook,HttpStatus.OK);
    }




    private void validateInputData(Integer fieldValue, String fieldName) {
        if (isNull(fieldValue) || !fieldValue.toString().matches("[0-9]+")) {
            throw new InvalidDataException(fieldName + " must be non-null. Allowed values are numbers[ex. 1 or 2 or 22]");
        }
    }

    private void validateBlockValue(String block) {
        if (isNull(block)) {
            throw new InvalidDataException("block value is invalid. Allowed values [yes or no] only.");
        }
    }
}
