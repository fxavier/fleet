package com.xavier.fleet.service;

import com.xavier.fleet.model.PurchaseOrder;
import com.xavier.fleet.model.PurchaseOrderItem;
import com.xavier.fleet.repository.PurchaseOrderItemRepository;
import com.xavier.fleet.repository.PurchaseOrderRepository;
import com.xavier.fleet.service.exception.PurchaseOrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderItemRepository itemRepository;

    @Autowired
    private PartService partService;


    @Transactional
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
       purchaseOrder.setDateIssued(LocalDate.now());
       purchaseOrder.setWorkOrder(purchaseOrder.getWorkOrder());
       purchaseOrder = purchaseOrderRepository.save(purchaseOrder);

       for (PurchaseOrderItem item : purchaseOrder.getItems()) {
           item.setPart(partService.findById(item.getPart().getId()));
           item.setPrice(item.getPart().getPrice());
           item.setPurchaseOrder(purchaseOrder);
        //   itemRepository.save(item);
       }
       itemRepository.saveAll(purchaseOrder.getItems());
       System.out.println(">>>>>>>>>>>>" + purchaseOrder.getItems().size());
       return purchaseOrder;
    }

    public PurchaseOrder findById(Long id) {
        verifyIfNotExist(id);
        return purchaseOrderRepository.getOne(id);
    }

    private void verifyIfNotExist(Long id) throws PurchaseOrderNotFoundException {
        Optional<PurchaseOrder> foundOrder = purchaseOrderRepository.findById(id);
        if (!foundOrder.isPresent()) {
            throw new PurchaseOrderNotFoundException();
        }
    }
}
