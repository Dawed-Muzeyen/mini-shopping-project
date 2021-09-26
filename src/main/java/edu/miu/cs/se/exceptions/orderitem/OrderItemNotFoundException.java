package edu.miu.cs.se.exceptions.orderitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderItemNotFoundException extends RuntimeException {
    private String message;
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
