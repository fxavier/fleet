package com.xavier.fleet.repository.part;

import com.xavier.fleet.model.Part;
import com.xavier.fleet.repository.filter.PartFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartRepositoryQuery {

    Page<Part> filter(PartFilter partFilter, Pageable pageable);
}
