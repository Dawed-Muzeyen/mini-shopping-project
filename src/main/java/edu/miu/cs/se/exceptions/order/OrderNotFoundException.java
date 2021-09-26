package edu.miu.cs.se.exceptions.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    private String message;
    public OrderNotFoundException(String message) {
        super(message);
    }
}
