package com.xavier.fleet.repository;

import com.xavier.fleet.model.PurchaseOrder;
import com.xavier.fleet.repository.po.PurchaseOrderRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, PurchaseOrderRepositoryQuery {
    Optional<PurchaseOrder> findByWorkOrderId(Long id);
}
