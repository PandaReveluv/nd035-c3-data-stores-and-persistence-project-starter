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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Pet updatePet(Pet pet) {

        return petRepository.save(pet);
    }

    public Pet getPetById(Long petId) {
        Optional<Pet> pet = petRepository.findById(petId);
        if (!pet.isPresent()) {
            throw new CustomNotFoundException("Cannot found pet with id: " + petId);
        }
        return pet.get();
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {

        List<Pet> result = new ArrayList<>();
        try {
            Customer customer = customerService.getCustomerById(ownerId);
            result = petRepository.findAllByCustomer(customer);
        } catch (CustomNotFoundException exception) {
            throw new BadRequestException(exception.getMessage());
        }
        return result;
    }
}
