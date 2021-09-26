package edu.miu.cs.se.exceptions.orderitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class OrderItemNotDeletedException extends RuntimeException {
    private String message;
    public OrderItemNotDeletedException(String message) {
        super(message);
    }
}
