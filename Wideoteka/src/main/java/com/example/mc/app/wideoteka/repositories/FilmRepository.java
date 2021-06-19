package com.example.mc.app.wideoteka.repositories;

import com.example.mc.app.wideoteka.model.Actors;
import com.example.mc.app.wideoteka.model.Director;
import com.example.mc.app.wideoteka.model.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {
    List<Film> getAllByDirectorsIsContaining(Director director);
    Object getAllByActorsIsContaining(Actors actors);

    Object getAllByDirectorIsContaining(Director director);
}
