package com.xavier.fleet.service;

import com.xavier.fleet.model.WorkOrder;
import com.xavier.fleet.repository.WorkOrderRepository;
import com.xavier.fleet.service.exception.WorkOrderExistException;
import com.xavier.fleet.service.exception.WorkOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    public WorkOrderService(@Autowired WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public WorkOrder save(final  WorkOrder workOrder) {
        verifyIfWorkOrderExist(workOrder);
        return workOrderRepository.save(workOrder);
    }

    public WorkOrder findById(Long id) {
        verifyIfWorkOrderNotExist(id);
        return workOrderRepository.getOne(id);
    }

    public void delete(Long id) {
        verifyIfWorkOrderNotExist(id);
        workOrderRepository.deleteById(id);
    }

    private void verifyIfWorkOrderNotExist(Long id) throws WorkOrderNotFoundException {
        Optional<WorkOrder> foundWorkOrder = workOrderRepository.findById(id);
        if (!foundWorkOrder.isPresent()) {
            throw new WorkOrderNotFoundException();
        }
    }

    private void verifyIfWorkOrderExist(WorkOrder workOrder) throws WorkOrderExistException {
        Optional<WorkOrder> foundWorkOrder = workOrderRepository
                .findByDateScheduledAndCustomerId(workOrder.getDateScheduled(), workOrder.getCustomer().getId());
        if (foundWorkOrder.isPresent() && (workOrder.isNew() || isUpdatingToADifferentEntity(workOrder, foundWorkOrder))) {
            throw new WorkOrderExistException();
        }
    }

    private boolean isUpdatingToADifferentEntity(WorkOrder workOrder, Optional<WorkOrder> foundWorkOrder) {
        return workOrder.exist() && !workOrder.equals(foundWorkOrder.get());
    }


}
