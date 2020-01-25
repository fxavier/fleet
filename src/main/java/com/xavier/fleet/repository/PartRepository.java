package com.xavier.fleet.repository;

import com.xavier.fleet.model.Part;
import com.xavier.fleet.repository.part.PartRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long>, PartRepositoryQuery {
    Optional<Part> findByName(String name);
}
