package com.ht.library.books.util;

import com.ht.library.books.entity.Book;
import com.ht.library.books.handlers.InvalidDataException;

import java.util.List;

import static com.ht.library.books.util.LibraryAppConstants.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidationUtil {

    private static final List<String> flagValues = List.of("true","false");

    private ValidationUtil() {
    }

    public static void validateInput(Book book) {
        validateInputValue(book.getBookName(), "bookName");
        validateInputValue(book.getAuthor(), "Author");
        validateInputValue(book.getGenre(), "Genre");


        if(book.getPrice()!=null){
            validateInputValue(book.getPrice().toString(), "Price");
        }else {
            throw new InvalidDataException("Price must not be null or blank. Please update price to "+book.getBookName()+" book");

        }


        if(book.getIsBookBorrowed() != null){
            validateBookStatusChange(book.getIsBookBorrowed().toString());
        }else {
            throw new InvalidDataException(PLEASE_PROVIDE_EITHER_TRUE_OR_FALSE_VALUE_ONLY);
        }
    }

    private static void validateInputValue(String value, String fieldName) {
        if (isBlank(value)) {
            throw new InvalidDataException(fieldName + " must not be empty or blank. Please provide a valid value");
        }
    }

    public static void validateBookStatusChange(String bookStatusUpdate) {
        if (isBlank(bookStatusUpdate) || !flagValues.contains(bookStatusUpdate)) {
            throw new InvalidDataException(BOOK_STATUS_VALUE_IS_EITHER_TRUE_OR_FALSE_ONLY);
        }
    }
}
