package com.xavier.fleet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String number;

    @NotNull(message ="workOrder-1")
    @Column(name = "date_scheduled")
    private LocalDate dateScheduled;

    @Column(name = "date_due")
    private LocalDate dateDue;

    @Column(name = "date_started")
    private LocalDate dateStarted;

    @Column(name = "date_completed")
    private LocalDate dateCompleted;

    @NotNull(message = "workOrder-2")
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull(message = "workorder-3")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public boolean isNew() {
        return this.id == null;
    }

    public boolean exist() {
        return this.id != null;
    }
}
