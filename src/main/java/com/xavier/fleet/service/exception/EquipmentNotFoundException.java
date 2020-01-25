package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class EquipmentNotFoundException extends BusnessException {
    public EquipmentNotFoundException() {
        super("equipment-2", HttpStatus.BAD_REQUEST);
    }
}
