package com.xavier.fleet.service;

import com.xavier.fleet.model.Customer;
import com.xavier.fleet.repository.CustomerRepository;
import com.xavier.fleet.service.exception.CustomerExistException;
import com.xavier.fleet.service.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(final Customer customer) {
        verifyIfExistCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        verifyIfCustomerNotExist(id);
        return customerRepository.getOne(id);
    }

    public void delete(Long id) {
        verifyIfCustomerNotExist(id);
        customerRepository.deleteById(id);
    }

    private void verifyIfCustomerNotExist(Long id) throws CustomerNotFoundException {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (!foundCustomer.isPresent()) {
            throw new CustomerNotFoundException();
        }
    }

    private void verifyIfExistCustomer(Customer customer) throws CustomerExistException {
        Optional<Customer> foundCustomer = customerRepository.findByNameOrEmail(customer.getName(), customer.getEmail());
        if (foundCustomer.isPresent() && (customer.isNew() || isUpdatingToADifferentEntity(customer, foundCustomer))) {
            throw new CustomerExistException();
        }
    }

    private boolean isUpdatingToADifferentEntity(Customer customer, Optional<Customer> foundCustomer) {
        return customer.exist() && !customer.equals(foundCustomer.get());
    }
}
