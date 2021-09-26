package edu.miu.cs.se.exceptions.shoppingcart;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ShoppingCartNotSavedException extends RuntimeException {
    private String message;
    public ShoppingCartNotSavedException(String message) {
        super(message);
    }
}
