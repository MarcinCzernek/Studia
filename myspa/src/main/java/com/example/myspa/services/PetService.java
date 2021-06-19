package com.example.myspa.services;

import com.example.myspa.model.Pet;
import com.example.myspa.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository){
        this.petRepository = petRepository;
    }

    @Transactional
    public Long add(Pet pet){
        return petRepository.save(pet).getId();
    }

    public void delete (Long id){
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()){
            petRepository.delete(pet.get());
        }

    }

    @Transactional
    public Optional<Pet> get (Long id){
        return petRepository.findById(id);

    }

    @Transactional
    public List<Pet> get(){
        return petRepository.findAll();
    }

    @Transactional
    public Pet update(Long id, String name){
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()){
            pet.get().setName(name);
            petRepository.save(pet.get());
        }
        return null;
    }
}

