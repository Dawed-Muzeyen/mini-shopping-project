package edu.miu.cs.se.exceptions.shoppingcart;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class ShoppingCartIdNotFoundException extends RuntimeException {
    private String message;
    public ShoppingCartIdNotFoundException(String message) {
        super(message);
    }
}
