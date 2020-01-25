package com.xavier.fleet.repository.po;

import com.xavier.fleet.model.PurchaseOrder;
import com.xavier.fleet.repository.filter.PurchaseOrderFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseOrderRepositoryQuery {
    Page<PurchaseOrder> filter(PurchaseOrderFilter purchaseOrderFilter, Pageable pageable);
}
