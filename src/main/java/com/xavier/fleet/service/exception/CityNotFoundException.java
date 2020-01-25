package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CityNotFoundException extends BusnessException {
    public CityNotFoundException() {
        super("city-4", HttpStatus.BAD_REQUEST);
    }
}
