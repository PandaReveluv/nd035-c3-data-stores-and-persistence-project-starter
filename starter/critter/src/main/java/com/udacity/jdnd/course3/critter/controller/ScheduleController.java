package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private PetService petService;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule schedule = scheduleService.addNewSchedule(scheduleDTO);

        List<Pet> pets = schedule.getPetIds().stream()
                .map(pet -> petService.getPetById(pet.getId()))
                .collect(Collectors.toList());
        pets.forEach(pet -> {
            pet.setSchedule(schedule);
            petService.updatePet(pet);
        });

        List<Employee> employees = schedule.getEmployeeIds().stream()
                .map(employee -> employeeService.getEmployeeById(employee.getId()))
                .collect(Collectors.toList());
        employees.forEach(employee -> {
            employee.setSchedule(schedule);
            employeeService.updateEmployee(employee);
        });

        return scheduleMapper.scheduleToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        return scheduleService.getAllSchedules().stream()
                .map(schedule -> scheduleMapper.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> schedules = scheduleService.getSchedulesByPetId(petId);
        return schedules.stream()
                .distinct()
                .map(schedule -> scheduleMapper.scheduleToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
