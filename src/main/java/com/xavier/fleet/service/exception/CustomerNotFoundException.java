package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends BusnessException {
    public CustomerNotFoundException() {
        super("customer-8", HttpStatus.BAD_REQUEST);
    }
}
