package com.xavier.fleet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipment")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String make;

    @Column(name = "number_plate")
    private String numberPlate;

    private String model;

    private Long year;

    private String chassi;

    private String engine;

    private String description;

    @NotNull(message = "equipment-1")
    @ManyToOne
    @JoinColumn(name = "type_id")
    private EquipmentType equipmentType;

    public boolean isNew() {
        return this.id == null;
    }

    public boolean exist() {
        return this.id != null;
    }
}
