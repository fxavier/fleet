package com.xavier.fleet.repository.Customer;

import com.xavier.fleet.model.Customer;
import com.xavier.fleet.repository.filter.CustomerFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerRepositoryQuery {

    Page<Customer> filter(CustomerFilter customerFilter, Pageable pageable);
}
