package com.xavier.fleet.repository;


import com.xavier.fleet.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    Optional<Province> findByProvinceName(String name);
}
