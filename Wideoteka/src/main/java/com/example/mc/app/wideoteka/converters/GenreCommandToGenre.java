package com.example.mc.app.wideoteka.converters;

import com.example.mc.app.wideoteka.command.GenreCommand;
import com.example.mc.app.wideoteka.model.Genre;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GenreCommandToGenre implements Converter<GenreCommand, Genre> {

    @Synchronized
    @Nullable
    @Override
    public Genre convert(GenreCommand source) {
        if (source == null) {
            return null;
        }

        final Genre genre = new Genre();
        genre.setName(source.getName());


        return genre;
    }
}

