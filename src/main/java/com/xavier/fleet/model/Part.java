package com.xavier.fleet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "part")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "part-1")
    private String name;

    private String description;

    @NotNull(message = "part-2")
    private Integer stock;

    @NotNull(message = "part-3")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull(message = "part-4")
    private BigDecimal price;

    public boolean isNew() {
        return this.id == null;
    }

    public boolean exist() {
        return this.id != null;
    }
}
