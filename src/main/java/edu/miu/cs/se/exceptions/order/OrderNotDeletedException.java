package edu.miu.cs.se.exceptions.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class OrderNotDeletedException extends RuntimeException {
    private String message;
    public OrderNotDeletedException(String message) {
        super(message);
    }
}
