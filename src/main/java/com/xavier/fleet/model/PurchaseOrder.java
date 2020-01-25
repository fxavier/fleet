package com.xavier.fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "purchase_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String number;

    @Column(name = "date_issued")
    private LocalDate dateIssued;


    @ManyToOne
    @JoinColumn(name = "work_order_id")
    private  WorkOrder workOrder;

    @OneToMany(mappedBy = "id.purchaseOrder")
    private Set<PurchaseOrderItem> items = new HashSet<>();

    public BigDecimal getTotal() {
        return items.stream()
                .map(PurchaseOrderItem::getSubtotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

}
