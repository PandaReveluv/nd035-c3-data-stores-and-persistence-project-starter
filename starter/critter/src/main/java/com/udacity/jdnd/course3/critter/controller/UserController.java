package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.users.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.users.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.users.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){

        return customerMapper.customerToCustomerDTO(customerService.addNewCustomer(customerDTO));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){

        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customer -> customerMapper.customerToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        return customerMapper.customerToCustomerDTO(customerService.getOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeDTO saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {

        return employeeMapper.employeeToEmployeeDTO(employeeService.addNewEmployee(employeeDTO));
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        return employeeMapper.employeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateEmployeeAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeService.findEmployeesForService(employeeDTO)
                .forEach(employee -> employeeDTOs.add(employeeMapper.employeeToEmployeeDTO(employee)));
        return employeeDTOs;
    }
}
