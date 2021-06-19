package com.example.mc.app.wideoteka.converters;

import com.example.mc.app.wideoteka.command.DirectorCommand;
import com.example.mc.app.wideoteka.model.Director;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DirectorCommandToDirector implements Converter<DirectorCommand, Director> {

    @Synchronized
    @Nullable
    @Override
    public Director convert(DirectorCommand source) {
        if (source == null) {
            return null;
        }

        final Director director = new Director();
        director.setFirstName(source.getFirstName());
        director.setLastName(source.getLastName());

        return director;


    }

}
