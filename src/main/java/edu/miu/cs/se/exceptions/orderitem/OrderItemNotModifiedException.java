package edu.miu.cs.se.exceptions.orderitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class OrderItemNotModifiedException extends RuntimeException {
    private String message;
    public OrderItemNotModifiedException(String message) {
        super(message);
    }
}
