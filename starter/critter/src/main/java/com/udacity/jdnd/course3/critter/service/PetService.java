package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.BadRequestException;
import com.udacity.jdnd.course3.critter.exception.CustomNotFoundException;
import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private CustomerMapper customerMapper;

    public Pet addNewPet(PetDTO petDTO) {
        Pet result;
        try {
            Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
            result = petRepository.save(petMapper.petDTOToPet(petDTO, customer, null));
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }

    public Pet getPetById(Long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new CustomNotFoundException("Cannot found pet with id: " + petId);
        }
        return pet.get();
    }
}
