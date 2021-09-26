package edu.miu.cs.se.exceptions.customer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CustomerNotDeletedException extends RuntimeException {
    private String message;
    public CustomerNotDeletedException(String message) {
        super(message);
    }
}
