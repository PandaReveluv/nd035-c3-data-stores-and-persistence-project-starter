package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue
    private long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Employee> employeeIds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Pet> petIds;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=EmployeeSkill.class)
    private Set<EmployeeSkill> activities;
}
