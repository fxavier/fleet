package com.xavier.fleet.resource;

import com.xavier.fleet.model.WorkOrder;
import com.xavier.fleet.repository.WorkOrderRepository;
import com.xavier.fleet.repository.filter.WorkOrderFilter;
import com.xavier.fleet.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/work_orders")
public class WorkOrderResource {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping
    public Page<WorkOrder> search(WorkOrderFilter workOrderFilter, Pageable pageable) {
        return workOrderRepository.filter(workOrderFilter, pageable);
    }

    @GetMapping("/{id}")
    public WorkOrder findById(@PathVariable Long id) {
        return workOrderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOrder create(@Valid @RequestBody WorkOrder workOrder) {
        return workOrderService.save(workOrder);
    }

    @PutMapping("/{id}")
    public WorkOrder update(@PathVariable Long id, @Valid @RequestBody WorkOrder workOrder) {
        workOrder.setId(id);
        return workOrderService.save(workOrder);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workOrderService.delete(id);
    }
}
