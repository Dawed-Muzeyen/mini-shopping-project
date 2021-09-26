package edu.miu.cs.se.exceptions.shoppingcartitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ShoppingCartItemNotDeletedException extends RuntimeException {
    private String message;
    public ShoppingCartItemNotDeletedException(String message) {
        super(message);
    }
}
