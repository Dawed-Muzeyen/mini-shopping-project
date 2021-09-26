package edu.miu.cs.se.exceptions.orderitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class OrderItemNotSavedException extends RuntimeException {
    private String message;
    public OrderItemNotSavedException(String message) {
        super(message);
    }
}
