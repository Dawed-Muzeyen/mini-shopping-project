package edu.miu.cs.se.exceptions.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class CustomerIdNotFoundException extends RuntimeException {
    private String message;
    public CustomerIdNotFoundException(String message) {
        super(message);
    }
}
