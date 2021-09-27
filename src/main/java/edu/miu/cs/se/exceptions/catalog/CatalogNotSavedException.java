package edu.miu.cs.se.exceptions.catalog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CatalogNotSavedException extends RuntimeException {
    private String message;
    public CatalogNotSavedException(String message) {
        super(message);
    }
}
