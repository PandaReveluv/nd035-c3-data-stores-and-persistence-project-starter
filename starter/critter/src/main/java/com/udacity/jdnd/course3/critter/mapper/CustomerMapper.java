package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.users.CustomerDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPetIds(null);
        return customer;
    }

    public CustomerDTO customerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setPetIds(null);
        return customerDTO;
    }
}
