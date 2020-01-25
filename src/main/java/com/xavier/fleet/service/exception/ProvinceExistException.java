package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class ProvinceExistException extends BusnessException {
    public ProvinceExistException() {
        super("province-2", HttpStatus.BAD_REQUEST);
    }
}
