package edu.miu.cs.se.exceptions.orderitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class OrderItemIdNotFoundException extends RuntimeException {
    private String message;
    public OrderItemIdNotFoundException(String message) {
        super(message);
    }
}
