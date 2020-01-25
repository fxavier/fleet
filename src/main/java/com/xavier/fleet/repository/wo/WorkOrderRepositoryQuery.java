package com.xavier.fleet.repository.wo;

import com.xavier.fleet.model.WorkOrder;
import com.xavier.fleet.repository.filter.WorkOrderFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkOrderRepositoryQuery {

    Page<WorkOrder> filter(WorkOrderFilter workOrderFilter, Pageable pageable);
}
