package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class WorkOrderNotFoundException extends BusnessException {
    public WorkOrderNotFoundException() {
        super("workorder-4", HttpStatus.BAD_REQUEST);
    }
}
