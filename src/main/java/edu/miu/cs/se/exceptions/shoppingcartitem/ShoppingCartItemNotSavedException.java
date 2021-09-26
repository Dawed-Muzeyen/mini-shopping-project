package edu.miu.cs.se.exceptions.shoppingcartitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ShoppingCartItemNotSavedException extends RuntimeException {
    private String message;
    public ShoppingCartItemNotSavedException(String message) {
        super(message);
    }
}
