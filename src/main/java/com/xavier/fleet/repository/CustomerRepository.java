package com.xavier.fleet.repository;

import com.xavier.fleet.model.Customer;
import com.xavier.fleet.repository.Customer.CustomerRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryQuery {
    Optional<Customer> findByNameOrEmail(String name, String email);
}
