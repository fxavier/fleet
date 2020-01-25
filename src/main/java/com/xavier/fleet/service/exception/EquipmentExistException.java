package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class EquipmentExistException extends BusnessException {
    public EquipmentExistException() {
        super("equipment-3", HttpStatus.BAD_REQUEST);
    }
}
