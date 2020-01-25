package com.xavier.fleet.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PurchaseOrderFilter {

    private String number;
    // private LocalDate dateIssued;
    private String customer;

}
