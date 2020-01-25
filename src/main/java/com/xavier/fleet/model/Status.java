package com.xavier.fleet.model;

import lombok.Getter;

public enum Status {

    ACTIVO("Activo"),
    INACTIVO("Inactivo");

    @Getter
    private String description;

    Status(String description) {
        this.description = description;
    }
}
