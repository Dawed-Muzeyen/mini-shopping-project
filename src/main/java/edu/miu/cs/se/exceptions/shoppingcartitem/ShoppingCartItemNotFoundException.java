package edu.miu.cs.se.exceptions.shoppingcartitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartItemNotFoundException extends RuntimeException {
    private String message;
    public ShoppingCartItemNotFoundException(String message) {
        super(message);
    }
}
