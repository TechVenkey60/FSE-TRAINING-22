package com.vrk.bank.portal.handlers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    private final Integer errorCode;
    private final String message;

    public DataNotFoundException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
