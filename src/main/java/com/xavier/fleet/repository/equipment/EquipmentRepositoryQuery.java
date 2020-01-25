package com.xavier.fleet.repository.equipment;

import com.xavier.fleet.model.Equipment;
import com.xavier.fleet.repository.filter.EquipmentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipmentRepositoryQuery {
    Page<Equipment> filter(EquipmentFilter equipmentFilter, Pageable pageable);
}
