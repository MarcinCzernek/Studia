package com.example.myspa.controllers;


import com.example.myspa.model.Pet;
import com.example.myspa.model.User;
import com.example.myspa.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    PetService petService;
    PetController(PetService petService){
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<Pet>> list(){
        return new ResponseEntity(petService.get(), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Long> addPet(@RequestBody Pet pet){
        return new ResponseEntity(petService.add(pet), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> update(@PathVariable Long id, @RequestBody Pet pet) {
        Pet newPet = petService.update(id,pet.getName());
        return new ResponseEntity<>(newPet,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> get(@PathVariable Long id) {
        Optional<Pet> pet = petService.get(id);

        if (pet.isPresent()){
            return new ResponseEntity(pet.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }


}
