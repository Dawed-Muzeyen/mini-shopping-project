package edu.miu.cs.se.exceptions.catalog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class CatalogIdNotFoundException extends RuntimeException {
    private String message;
    public CatalogIdNotFoundException(String message) {
        super(message);
    }
}
