package com.xavier.fleet.service.exception;

import org.springframework.http.HttpStatus;

public class PurchaseOrderNotFoundException extends BusnessException {
    public PurchaseOrderNotFoundException() {
        super("purchaseorder-1", HttpStatus.BAD_REQUEST);
    }
}
