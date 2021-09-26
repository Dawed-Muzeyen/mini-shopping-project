package edu.miu.cs.se.exceptions.product;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProductIdNotFoundException extends RuntimeException {
    private String message;
    public ProductIdNotFoundException(String message) {
        super(message);
    }
}
