package edu.miu.cs.se.exceptions.shoppingcartitem;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class ShoppingCartItemIdNotFoundException extends RuntimeException {
    private String message;
    public ShoppingCartItemIdNotFoundException(String message) {
        super(message);
    }
}
