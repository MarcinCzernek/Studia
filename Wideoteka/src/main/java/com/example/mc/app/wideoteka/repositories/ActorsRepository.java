package com.example.mc.app.wideoteka.repositories;

import com.example.mc.app.wideoteka.model.Actors;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ActorsRepository extends CrudRepository<Actors, Long> {
    List<Actors> getActorsByFirstName(String firstName);
    Optional<Actors> getFirstByFirstNameAndLastName(String firstName, String lastName);
}
