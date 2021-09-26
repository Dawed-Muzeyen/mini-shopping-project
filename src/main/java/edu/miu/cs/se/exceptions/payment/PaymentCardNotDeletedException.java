package edu.miu.cs.se.exceptions.payment;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class PaymentCardNotDeletedException extends RuntimeException {
    private String message;
    public PaymentCardNotDeletedException(String message) {
        super(message);
    }
}
