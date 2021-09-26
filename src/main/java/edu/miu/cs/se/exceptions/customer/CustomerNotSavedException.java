package edu.miu.cs.se.exceptions.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CustomerNotSavedException extends RuntimeException {
    private String message;
    public CustomerNotSavedException(String message) {
        super(message);
    }
}
