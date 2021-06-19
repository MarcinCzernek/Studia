package com.example.mc.app.wideoteka.converters;

import com.example.mc.app.wideoteka.command.ActorsCommand;
import com.example.mc.app.wideoteka.model.Actors;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ActorsCommandToActors implements Converter<ActorsCommand, Actors> {

    @Synchronized
    @Nullable
    @Override
    public Actors convert(ActorsCommand source) {
        if (source == null) {
            return null;
        }
        final Actors actors = new Actors();
        actors.setFirstName(source.getFirstName());
        actors.setLastName(source.getLastName());

        return actors;

    }

}
