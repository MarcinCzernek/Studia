package com.example.mc.app.wideoteka.converters;

import com.example.mc.app.wideoteka.command.FilmCommand;
import com.example.mc.app.wideoteka.model.Actors;
import com.example.mc.app.wideoteka.model.Director;
import com.example.mc.app.wideoteka.model.Film;
import com.example.mc.app.wideoteka.repositories.ActorsRepository;
import com.example.mc.app.wideoteka.repositories.DirectorRepository;
import com.example.mc.app.wideoteka.repositories.GenreRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilmCommandToFilm implements Converter<FilmCommand, Film> {

    @Synchronized
    @Nullable
    @Override
    public Film convert(FilmCommand source) {
        if (source == null) {
            return null;
        }

        final Film film = new Film();
        film.setId(source.getId());
        film.setTitle(source.getTitle());
        film.setGenre(source.getGenre());
        film.setYear(source.getYear());
        film.setActors(source.getActors());
        film.setWriter(source.getWriter());

        return film;

    }

}

