package com.vrk.bank.portal.handlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AppExceptionHandlerTest {
    private static final String NO_DATA_FOUND = "No Data Found";
    private static final String INVALID_USER_NAME_PASSWORD = "Invalid userName/Password..";

    @InjectMocks
    private AppExceptionHandler appExceptionHandler;

    @Test
    void handleDataNotFoundExceptioTest() {
        var response = appExceptionHandler.handleDataNotFoundException(new DataNotFoundException(NO_DATA_FOUND,404));
        assertEquals(NO_DATA_FOUND,response.getBody().getMessage());
    }

    @Test
    void handleInvalidDataExceptionTest() {
        var response = appExceptionHandler.handleInvalidDataException(new InvalidDataException(INVALID_USER_NAME_PASSWORD));
        assertEquals(INVALID_USER_NAME_PASSWORD,response.getBody().getMessage());
    }
}
