package com.xavier.fleet.resource;

import com.xavier.fleet.model.Equipment;
import com.xavier.fleet.repository.EquipmentRepository;
import com.xavier.fleet.repository.filter.EquipmentFilter;
import com.xavier.fleet.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/equipments")
public class EquipmentResource {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public Page<Equipment> search(EquipmentFilter equipmentFilter, Pageable pageable) {
        return equipmentRepository.filter(equipmentFilter, pageable);
    }

    @GetMapping("/{id}")
    public Equipment findById(@PathVariable Long id) {
        return equipmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment create(@Valid @RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
    }

    @PutMapping("/{id}")
    public Equipment update(@PathVariable Long id, @Valid @RequestBody Equipment equipment) {
        equipment.setId(id);
        return equipmentService.save(equipment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipmentService.delete(id);
    }

}
