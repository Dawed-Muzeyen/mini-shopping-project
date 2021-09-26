package edu.miu.cs.se.exceptions.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CustomerNotModifiedException extends RuntimeException {
    private String message;
    public CustomerNotModifiedException(String message) {
        super(message);
    }
}
