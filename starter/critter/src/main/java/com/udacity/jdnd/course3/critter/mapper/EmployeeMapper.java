package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO, Schedule schedule) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setSchedule(schedule);
        return employee;
    }

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        return employeeDTO;
    }
}
