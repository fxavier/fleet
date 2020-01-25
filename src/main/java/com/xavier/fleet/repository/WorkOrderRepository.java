package com.xavier.fleet.repository;

import com.xavier.fleet.model.WorkOrder;
import com.xavier.fleet.repository.wo.WorkOrderRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long>, WorkOrderRepositoryQuery {

    Optional<WorkOrder> findByDateScheduledAndCustomerId(LocalDate date, Long id);
}
