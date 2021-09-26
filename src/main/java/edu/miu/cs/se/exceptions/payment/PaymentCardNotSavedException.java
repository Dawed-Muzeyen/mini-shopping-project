package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PaymentCardNotSavedException extends RuntimeException {
    private String message;
    public PaymentCardNotSavedException(String message) {
        super(message);
    }
}
