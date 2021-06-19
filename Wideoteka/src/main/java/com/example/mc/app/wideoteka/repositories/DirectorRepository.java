package com.example.mc.app.wideoteka.repositories;

import com.example.mc.app.wideoteka.model.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long> {
    Optional<Director> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
