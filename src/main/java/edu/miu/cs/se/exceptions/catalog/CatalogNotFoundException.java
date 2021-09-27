package edu.miu.cs.se.exceptions.catalog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CatalogNotFoundException extends RuntimeException {
    private String message;
    public CatalogNotFoundException(String message) {
        super(message);
    }
}
