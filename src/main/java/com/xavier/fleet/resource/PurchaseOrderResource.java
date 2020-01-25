package com.xavier.fleet.resource;

import com.xavier.fleet.model.PurchaseOrder;
import com.xavier.fleet.repository.PurchaseOrderRepository;
import com.xavier.fleet.repository.filter.PurchaseOrderFilter;
import com.xavier.fleet.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/purchase_orders")
public class PurchaseOrderResource {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public Page<PurchaseOrder> search(PurchaseOrderFilter purchaseOrderFilter, Pageable pageable) {
        return purchaseOrderRepository.filter(purchaseOrderFilter, pageable);
    }

    @GetMapping("/{id}")
    public PurchaseOrder findById(@PathVariable Long id) {
        return purchaseOrderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder create(@Valid @RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.save(purchaseOrder);
    }
}
