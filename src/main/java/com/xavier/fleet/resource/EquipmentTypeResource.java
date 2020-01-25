package com.xavier.fleet.resource;

import com.xavier.fleet.model.EquipmentType;
import com.xavier.fleet.repository.EquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment_types")
public class EquipmentTypeResource {

    @Autowired
    private EquipmentTypeRepository typeRepository;

    @GetMapping
    public List<EquipmentType> findAll() {
        return typeRepository.findAll();
    }
}
