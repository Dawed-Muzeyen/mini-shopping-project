package edu.miu.cs.se.exceptions.shoppingcart;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ShoppingCartNotModifiedException extends RuntimeException {
    private String message;
    public ShoppingCartNotModifiedException(String message) {
        super(message);
    }
}
