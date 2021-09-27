package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CreditCardNotModifiedException extends RuntimeException {
    private String message;
    public CreditCardNotModifiedException(String message) {
        super(message);
    }
}
