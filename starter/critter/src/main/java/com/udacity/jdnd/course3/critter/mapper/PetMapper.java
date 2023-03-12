package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public Pet petDTOToPet(PetDTO petDTO, Customer customer, Schedule schedule) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setCustomer(customer);
        pet.setSchedule(schedule);
        return pet;
    }

    public PetDTO petToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setNotes(pet.getNotes());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setBirthDate(pet.getBirthDate());
        return petDTO;
    }
}
