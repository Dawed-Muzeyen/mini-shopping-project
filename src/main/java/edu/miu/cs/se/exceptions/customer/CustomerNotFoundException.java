package edu.miu.cs.se.exceptions.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    private String message;
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
