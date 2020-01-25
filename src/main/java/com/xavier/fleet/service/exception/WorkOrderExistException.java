package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class WorkOrderExistException extends BusnessException {
    public WorkOrderExistException() {
        super("workorder-5", HttpStatus.BAD_REQUEST);
    }
}
