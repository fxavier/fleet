package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class ProvinceNotFoundException extends BusnessException {
    public ProvinceNotFoundException() {
        super("province-3", HttpStatus.BAD_REQUEST);
    }
}
