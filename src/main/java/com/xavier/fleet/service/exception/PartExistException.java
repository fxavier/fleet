package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class PartExistException extends BusnessException {
    public PartExistException() {
        super("part-5", HttpStatus.BAD_REQUEST);
    }
}
