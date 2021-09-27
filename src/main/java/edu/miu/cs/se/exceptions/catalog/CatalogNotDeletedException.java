package edu.miu.cs.se.exceptions.catalog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CatalogNotDeletedException extends RuntimeException {
    private String message;
    public CatalogNotDeletedException(String message) {
        super(message);
    }
}
