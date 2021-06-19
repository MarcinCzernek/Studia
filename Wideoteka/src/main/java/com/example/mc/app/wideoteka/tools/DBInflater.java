package com.example.mc.app.wideoteka.tools;

import com.example.mc.app.wideoteka.model.Actors;
import com.example.mc.app.wideoteka.model.Director;
import com.example.mc.app.wideoteka.model.Film;
import com.example.mc.app.wideoteka.model.Genre;
import com.example.mc.app.wideoteka.repositories.ActorsRepository;
import com.example.mc.app.wideoteka.repositories.DirectorRepository;
import com.example.mc.app.wideoteka.repositories.FilmRepository;
import com.example.mc.app.wideoteka.repositories.GenreRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    public DBInflater(ActorsRepository actorsRepository, DirectorRepository directorRepository, FilmRepository filmRepository, GenreRepository genreRepository) {
        this.actorsRepository = actorsRepository;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    private ActorsRepository actorsRepository;
    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) { initData();
    }

    private void initData() {

        Film lostHorizon = new Film( "Lost Horizon","adventure","Frank Capra","1937","Ronald Colman","Larry Kramer");
        Director frankCapra = new Director("Frank", "Capra");
        Actors ronaldColman = new Actors("Ronald", "Colman");
        Genre adventure = new Genre("Adventure");
        frankCapra.getFilms().add(lostHorizon);
        lostHorizon.getDirectors().add(frankCapra);
        genreRepository.save(adventure);
        filmRepository.save(lostHorizon);
        directorRepository.save(frankCapra);
        actorsRepository.save(ronaldColman);

        Film benHur = new Film( "Ben Hur", "Historical drama","William Wyler", "1959", "Charlton Heston","Lew Wallace");
        Director williamWyler = new Director("William", "Wyler");
        Actors charltonHeston = new Actors("Charlton", "Heston");
        Genre historicalDrama = new Genre("Historical drama");
        williamWyler.getFilms().add(benHur);
        benHur.getDirectors().add(williamWyler);
        genreRepository.save(historicalDrama);
        filmRepository.save(benHur);
        directorRepository.save(williamWyler);
        actorsRepository.save(charltonHeston);

        Film theSoundOfMusic = new Film("The Sound of Music","Musical","Robert Wise", "1965", "Julie Andrews","George Hurdalek");
        Director robertWise  = new Director("Robert","Wise");
        Actors julieAndrews = new Actors("Julie", "Andrews");
        Genre musical = new Genre("Musical");
        julieAndrews.getFilms().add(theSoundOfMusic);
        theSoundOfMusic.getDirectors().add(robertWise);
        genreRepository.save(musical);
        filmRepository.save(theSoundOfMusic);
        directorRepository.save(robertWise);
        actorsRepository.save(julieAndrews);

    }


}
