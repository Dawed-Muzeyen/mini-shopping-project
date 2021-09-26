package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class PaymentCardNotModifiedException extends RuntimeException {
    private String message;
    public PaymentCardNotModifiedException(String message) {
        super(message);
    }
}
