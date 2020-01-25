package com.xavier.fleet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "customer-1")
    private String name;

    @Column(name = "contact_person")
    private String contactPerson;

    @NotBlank(message = "customer-2")
    private String address;

    @NotBlank(message = "customer-3")
    private String phone1;

    private String phone2;

    private String fax;

    @NotBlank(message = "customer-4")
    private String email;

    private  String website;

    private String pobox;

    @NotNull(message = "customer-5")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "customer-6")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public boolean isNew() {
        return this.id == null;
    }

    public boolean exist() {
        return this.id != null;
    }
}
