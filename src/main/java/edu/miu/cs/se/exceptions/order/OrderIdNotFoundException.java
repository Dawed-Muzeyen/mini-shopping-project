package edu.miu.cs.se.exceptions.order;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class OrderIdNotFoundException extends RuntimeException {
    private String message;
    public OrderIdNotFoundException(String message) {
        super(message);
    }
}
