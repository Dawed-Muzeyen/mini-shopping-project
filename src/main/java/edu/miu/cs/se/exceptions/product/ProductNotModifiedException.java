package edu.miu.cs.se.exceptions.product;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ProductNotModifiedException extends RuntimeException {
    private String message;
    public ProductNotModifiedException(String message) {
        super(message);
    }
}
