package edu.miu.cs.se.exceptions.catalog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CatalogNotModifiedException extends RuntimeException {
    private String message;
    public CatalogNotModifiedException(String message) {
        super(message);
    }
}
