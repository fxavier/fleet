package com.xavier.fleet.repository;

import com.xavier.fleet.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByCityName(String name);
    List<City> findByProvinceProvinceId(Integer provinceId);
}
