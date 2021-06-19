package com.example.mc.app.wideoteka.repositories;

import com.example.mc.app.wideoteka.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> getGenreByName(String name);
}
