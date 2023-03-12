package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.BadRequestException;
import com.udacity.jdnd.course3.critter.exception.CustomNotFoundException;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private PetService petService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    public Schedule addNewSchedule(ScheduleDTO scheduleDTO) {
        List<Pet> pets = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        try {
            scheduleDTO.getPetIds().forEach(petId -> pets.add(petService.getPetById(petId)));
            scheduleDTO.getEmployeeIds().forEach(employeeId -> employees.add(employeeService.getEmployeeById(employeeId)));
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return scheduleRepository.save(scheduleMapper.scheduleDTOToSchedule(scheduleDTO, pets, employees));
    }

    public List<Schedule> getAllSchedules() {

        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long petId) {

        List<Schedule> result;
        try {
            result = scheduleRepository.findAllByPetIdsIn(Collections.singletonList(petService.getPetById(petId)));
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }

    public List<Schedule> getSchedulesByEmployeeId(Long employeeId) {

        List<Schedule> result;
        try {
            result = scheduleRepository.findAllByEmployeeIdsIn(Collections.singletonList(employeeService.getEmployeeById(employeeId)));
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }

    public List<Schedule> getSchedulesByCustomerId(Long customerId) {

        List<Schedule> result;
        try {
            List<Pet> pets = petService.getPetsByOwnerId(customerId);
            result = scheduleRepository.findAllByPetIdsIn(pets);
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }
}
