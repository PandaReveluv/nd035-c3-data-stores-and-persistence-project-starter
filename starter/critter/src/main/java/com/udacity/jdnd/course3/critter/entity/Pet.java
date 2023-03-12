package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.pet.PetType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne
    private Schedule schedule;
}
