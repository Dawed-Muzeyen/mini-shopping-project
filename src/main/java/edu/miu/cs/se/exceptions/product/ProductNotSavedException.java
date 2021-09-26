package edu.miu.cs.se.exceptions.product;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProductNotSavedException extends RuntimeException {
    private String message;
    public ProductNotSavedException(String message) {
        super(message);
    }
}
