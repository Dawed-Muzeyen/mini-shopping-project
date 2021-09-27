package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditCardNotFoundException extends RuntimeException {
    private String message;
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
