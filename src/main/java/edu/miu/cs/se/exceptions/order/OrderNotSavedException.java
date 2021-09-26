package edu.miu.cs.se.exceptions.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OrderNotSavedException extends RuntimeException {
    private String message;
    public OrderNotSavedException(String message) {
        super(message);
    }
}
