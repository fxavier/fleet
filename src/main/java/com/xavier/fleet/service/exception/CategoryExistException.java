package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CategoryExistException extends BusnessException {
    public CategoryExistException() {
        super("category-3", HttpStatus.BAD_REQUEST);
    }
}
