package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BusnessException {
    public CategoryNotFoundException() {
        super("category-2", HttpStatus.BAD_REQUEST);
    }
}
