package com.xavier.fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order_item")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchaseOrderItem {

    @JsonIgnore
    @EmbeddedId
    private PurchaseOrderItemPK id = new PurchaseOrderItemPK();

    private Integer quantity;

    private BigDecimal price;

    @JsonIgnore
    public PurchaseOrder getPurchaseOrder() {
        return id.getPurchaseOrder();
    }

    public Part getPart() {
        return id.getPart();
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        id.setPurchaseOrder(purchaseOrder);
    }

    public void setPart(Part part) {
        id.setPart(part);
    }

    public BigDecimal getSubtotal() {
        return price.multiply(new BigDecimal(quantity));
    }
}
