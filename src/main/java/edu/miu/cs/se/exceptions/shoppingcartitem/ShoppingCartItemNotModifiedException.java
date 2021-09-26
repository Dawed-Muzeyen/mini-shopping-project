package edu.miu.cs.se.exceptions.shoppingcartitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ShoppingCartItemNotModifiedException extends RuntimeException {
    private String message;
    public ShoppingCartItemNotModifiedException(String message) {
        super(message);
    }
}
