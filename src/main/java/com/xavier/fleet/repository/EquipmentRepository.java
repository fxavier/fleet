package com.xavier.fleet.repository;

import com.xavier.fleet.model.Equipment;
import com.xavier.fleet.repository.equipment.EquipmentRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long>, EquipmentRepositoryQuery {

    Optional<Equipment> findByNumberPlate(String plate);
}
