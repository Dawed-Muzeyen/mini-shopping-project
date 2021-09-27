package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CreditCardNotDeletedException extends RuntimeException {
    private String message;
    public CreditCardNotDeletedException(String message) {
        super(message);
    }
}
