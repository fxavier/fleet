package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CustomerExistException extends BusnessException {
    public CustomerExistException() {
        super("customer-7", HttpStatus.BAD_REQUEST);
    }
}
