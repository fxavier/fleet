package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CityExistException extends BusnessException {
    public CityExistException() {
        super("city-3", HttpStatus.BAD_REQUEST);
    }
}
