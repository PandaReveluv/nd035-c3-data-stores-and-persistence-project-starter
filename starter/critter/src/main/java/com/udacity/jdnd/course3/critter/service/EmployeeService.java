package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.users.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exception.CustomNotFoundException;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee addNewEmployee(EmployeeDTO employeeDTO) {
        return employeeRepository.save(employeeMapper.employeeDTOToEmployee(employeeDTO, null));
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new CustomNotFoundException("Cannot found employee with id: " + employeeId);
        }
        return employee.get();
    }

    public void updateEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {

        Employee employee = getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {

        List<Employee> result = new ArrayList<>();
        DayOfWeek dayOfWeek = employeeRequestDTO.getDate().getDayOfWeek();
        List<Employee> employees = employeeRepository.findAllBySkillsIn(employeeRequestDTO.getSkills());
        employeeRequestDTO.getSkills()
                .forEach(employeeSkill -> employees.retainAll(employeeRepository.findAllBySkillsIn(Collections.singleton(employeeSkill))));

        if (!CollectionUtils.isEmpty(employees)) {
            result = employees.stream()
                    .distinct()
                    .filter(employee -> employee.getDaysAvailable().contains(dayOfWeek))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
