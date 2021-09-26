package edu.miu.cs.se.exceptions.shoppingcart;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ShoppingCartNotDeletedException extends RuntimeException {
    private String message;
    public ShoppingCartNotDeletedException(String message) {
        super(message);
    }
}
