package com.digital.user.books.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    private Integer errorCode;
    private String message1;

    public DataNotFoundException(String message1, Integer errorCode) {
        super(message1);
        this.errorCode = errorCode;
        this.message1 = message1;
    }

    public DataNotFoundException(){
        super();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage1() {
        return message1;
    }
}
