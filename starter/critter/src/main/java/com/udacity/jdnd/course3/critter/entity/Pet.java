package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.pet.PetType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
