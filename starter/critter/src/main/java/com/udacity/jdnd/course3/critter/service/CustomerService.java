package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.users.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDTO addNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        return customerMapper.customerToCustomerDTO(customer);
    }

    public boolean isCustomerExisted(Long customerId) {
        return customerRepository.existsById(customerId);
    }

    public Customer getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("Cannot found customer with id: " + customerId);
        }
        return customer.get();
    }
}
