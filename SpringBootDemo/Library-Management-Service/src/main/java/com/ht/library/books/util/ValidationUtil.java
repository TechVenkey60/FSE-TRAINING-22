package com.ht.library.books.util;

import com.ht.library.books.entity.Book;
import com.ht.library.books.handlers.InvalidDataException;
import com.ht.library.books.model.BookStatusUpdate;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ht.library.books.util.LibraryAppConstants.FALSE;
import static com.ht.library.books.util.LibraryAppConstants.TRUE;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidationUtil {

    private ValidationUtil(){}

    public static void validateInput(Book book){
        validateInputValue(book.getBookName(),"bookName");
        validateInputValue(book.getAuthor(),"Author");
        validateInputValue(book.getGenre(),"Genre");
        validateInputValue(book.getPrice().toString(),"Price");
    }

    private static void validateInputValue(String value,String fieldName){
        if(isBlank(value)){
            throw new InvalidDataException(fieldName+" must not be empty or blank. Please provide a valid value");
        }
    }

    public static void validateBookStatuChange(BookStatusUpdate bookStatusUpdate) {
        if(isBlank(bookStatusUpdate.getIsBookBorrowed()) || !TRUE.equalsIgnoreCase(bookStatusUpdate.getIsBookBorrowed()) || !FALSE.equalsIgnoreCase(bookStatusUpdate.getIsBookBorrowed())){
            throw new InvalidDataException("InValid Data Found. Allowed Book Status value is either 'true' or 'false' only.");
        }
    }
}
