package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(employeeMapper.employeeDTOToEmployee(employeeDTO, null));
        return employeeMapper.employeeToEmployeeDTO(employee);
    }
}
