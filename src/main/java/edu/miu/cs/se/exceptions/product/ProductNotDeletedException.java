package edu.miu.cs.se.exceptions.product;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ProductNotDeletedException extends RuntimeException {
    private String message;
    public ProductNotDeletedException(String message) {
        super(message);
    }
}
