package com.xavier.fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "province")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "province_id")
    private Integer provinceId;

    @NotBlank(message = "province-1")
    @Column(name = "province_name")
    private String provinceName;

    @OneToMany(mappedBy = "province")
    @JsonIgnore
    private List<City> cities = new ArrayList<>();

    public boolean isNew() {
        return this.provinceId == null;
    }

    public boolean exist() {
        return this.getProvinceId() != null;
    }
}
