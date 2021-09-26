package edu.miu.cs.se.exceptions;


import edu.miu.cs.se.exceptions.customer.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    ResponseEntity<Object> handleApiException(CustomerNotFoundException ex, WebRequest request) {
        ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<Object>(responsePayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomerNotSavedException.class)
    ResponseEntity<Object> handleApiException(CustomerNotSavedException ex, WebRequest request) {
        ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<Object>(responsePayload, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = CustomerIdNotFoundException.class)
    ResponseEntity<Object> handleApiException(CustomerIdNotFoundException ex, WebRequest request) {
       ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.NO_CONTENT);

        return new ResponseEntity<Object>(responsePayload, HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(value = CustomerNotModifiedException.class)
    ResponseEntity<Object> handleApiException(CustomerNotModifiedException ex, WebRequest request) {
        ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.NOT_MODIFIED);

        return new ResponseEntity<Object>(responsePayload, HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(value = CustomerNotDeletedException.class)
    ResponseEntity<Object> handleApiException(CustomerNotDeletedException ex, WebRequest request) {
        ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<Object>(responsePayload, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<Object> handleApiException(Exception ex, WebRequest request) {
        ResponsePayload responsePayload = new ResponsePayload(ZonedDateTime.now(ZoneId.of("Z")), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(responsePayload, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
