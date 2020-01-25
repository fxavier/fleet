package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class PartNotFoundException extends BusnessException {
    public PartNotFoundException() {
        super("part-6", HttpStatus.BAD_REQUEST);
    }
}
