package com.xavier.fleet.service;

import com.xavier.fleet.model.Equipment;
import com.xavier.fleet.repository.EquipmentRepository;
import com.xavier.fleet.service.exception.EquipmentExistException;
import com.xavier.fleet.service.exception.EquipmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment findById(Long id) {
        verifyifequipmentNotExist(id);
        return equipmentRepository.getOne(id);
    }

    public Equipment save(final Equipment equipment) {
        verifyIfEquipmentExist(equipment);
        return equipmentRepository.save(equipment);
    }

    public void delete(Long id) {
        verifyifequipmentNotExist(id);
        equipmentRepository.deleteById(id);
    }

    private void verifyIfEquipmentExist(Equipment equipment) throws EquipmentExistException {
        Optional<Equipment> foundEquipment = equipmentRepository.findByNumberPlate(equipment.getNumberPlate());
        if (foundEquipment.isPresent() && (equipment.isNew() || isUpdatingToADifferentEntity(equipment, foundEquipment))) {
            throw new EquipmentExistException();
        }
    }

    private boolean isUpdatingToADifferentEntity(Equipment equipment, Optional<Equipment> foundEquipment) {
        return equipment.exist() && !equipment.equals(foundEquipment.get());
    }

    private void verifyifequipmentNotExist(Long id) throws EquipmentNotFoundException {
        Optional<Equipment> foundEquipment = equipmentRepository.findById(id);
        if (!foundEquipment.isPresent()) {
          throw new EquipmentNotFoundException();
        }
    }
}
