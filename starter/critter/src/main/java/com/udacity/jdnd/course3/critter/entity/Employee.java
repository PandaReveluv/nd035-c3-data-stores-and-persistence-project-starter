package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=EmployeeSkill.class)
    private Set<EmployeeSkill> skills;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany(mappedBy = "employeeIds", cascade = CascadeType.ALL)
    private List<Schedule> schedule;
}
