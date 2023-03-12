package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.pet.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @PostMapping
    public PetDTO savePet(@RequestBody @Valid PetDTO petDTO) {

        return petMapper.petToPetDTO(petService.addNewPet(petDTO));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return petMapper.petToPetDTO(petService.getPetById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<Pet> pets = petService.getAllPets();
        return pets.stream().map(pet -> petMapper.petToPetDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        List<Pet> pets = petService.getPetsByOwnerId(ownerId);
        return pets.stream().map(pet -> petMapper.petToPetDTO(pet)).collect(Collectors.toList());
    }
}
