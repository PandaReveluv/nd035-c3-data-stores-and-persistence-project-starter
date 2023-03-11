package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.users.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
