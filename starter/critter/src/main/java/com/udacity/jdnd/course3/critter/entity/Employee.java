package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.users.EmployeeSkill;
import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=EmployeeSkill.class)
    private Set<EmployeeSkill> skills;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass=DayOfWeek.class)
    private Set<DayOfWeek> daysAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
