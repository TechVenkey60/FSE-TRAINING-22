package com.digital.books.controller;

import com.digital.books.handlers.InvalidDataException;
import com.digital.books.model.DigitalBook;
import com.digital.books.service.IDigitalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.isNull;

@RestController
public class DigitalBooksController {

    @Autowired
    private IDigitalBookService digitalBookService;

    @PostMapping("/{authorId}/books")
    public ResponseEntity<DigitalBook> saveDigitalBook(@PathVariable Integer authorId,
                                                       @RequestBody @Valid DigitalBook digitalBook) {

        validateInputData(authorId, "authorId");
        var savedDigitalBook = digitalBookService.createDigitalBook(digitalBook, authorId);
        return new ResponseEntity<>(savedDigitalBook, HttpStatus.OK);
    }


    @PutMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<DigitalBook> updateDigitalBook(@PathVariable Integer authorId,
                                                         @PathVariable Integer bookId,
                                                         @RequestBody DigitalBook digitalBook) {
        validateInputData(authorId, "authorId");
        validateInputData(bookId, "bookId");

        var updatedDigitalBook = digitalBookService.updateDigitalBook(digitalBook, authorId, bookId);
        return new ResponseEntity<>(updatedDigitalBook, HttpStatus.OK);
    }


    @PostMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<String> updateDigitalBooksVisibility(@RequestParam(defaultValue = "no") String block,
                                                               @PathVariable Integer authorId,
                                                               @PathVariable Integer bookId) {

        //validate input
        validateInputData(authorId, "authorId");
        validateInputData(bookId, "bookId");
        validateBlockValue(block);

        var response = digitalBookService.updateBookVisibility(authorId, bookId, block);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validateInputData(Integer fieldValue, String fieldName) {
        if (isNull(fieldValue) || !fieldValue.toString().matches("[0-9]+")) {
            throw new InvalidDataException(fieldName + " must be non-null. Allowed values are numbers[ex. 1 or 2 or 22]");
        }
    }

    private void validateBlockValue(String block) {
        if (isNull(block) || !"yes".equalsIgnoreCase(block) || !"no".equalsIgnoreCase(block)) {
            throw new InvalidDataException("block value is invalid. Allowed values [yes or no] only.");
        }
    }
}
