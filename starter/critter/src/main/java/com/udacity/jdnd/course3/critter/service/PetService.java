package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.BadRequestException;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetMapper petMapper;

    public PetDTO addNewPet(PetDTO petDTO) {
        PetDTO result = new PetDTO();
        try {
            Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
            Pet pet = petRepository.save(petMapper.petDTOToPet(petDTO, customer, null));
            result = petMapper.petToPetDTO(pet);
        } catch (CustomerNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }
}
