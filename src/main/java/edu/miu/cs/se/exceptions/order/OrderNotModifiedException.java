package edu.miu.cs.se.exceptions.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class OrderNotModifiedException extends RuntimeException {
    private String message;
    public OrderNotModifiedException(String message) {
        super(message);
    }
}
