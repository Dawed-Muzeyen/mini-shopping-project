package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NO_CONTENT)
public class CreditCardIdNotFoundException extends RuntimeException {
    private String message;
    public CreditCardIdNotFoundException(String message) {
        super(message);
    }
}
