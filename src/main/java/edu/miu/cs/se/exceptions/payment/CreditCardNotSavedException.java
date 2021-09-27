package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CreditCardNotSavedException extends RuntimeException {
    private String message;
    public CreditCardNotSavedException(String message) {
        super(message);
    }
}
