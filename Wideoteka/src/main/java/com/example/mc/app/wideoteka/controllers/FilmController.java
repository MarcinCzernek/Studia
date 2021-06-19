package com.example.mc.app.wideoteka.controllers;

import com.example.mc.app.wideoteka.command.FilmCommand;
import com.example.mc.app.wideoteka.converters.FilmCommandToFilm;
import com.example.mc.app.wideoteka.model.Film;
import com.example.mc.app.wideoteka.repositories.ActorsRepository;
import com.example.mc.app.wideoteka.repositories.DirectorRepository;
import com.example.mc.app.wideoteka.repositories.FilmRepository;
import com.example.mc.app.wideoteka.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilmController {

    private ActorsRepository actorsRepository;
    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;
    private FilmCommandToFilm filmCommandToFilm;

    public FilmController(ActorsRepository actorsRepository, DirectorRepository directorRepository, FilmRepository filmRepository, GenreRepository genreRepository, FilmCommandToFilm filmCommandToFilm) {
        this.actorsRepository = actorsRepository;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.filmCommandToFilm = filmCommandToFilm;
    }

    @GetMapping
    @RequestMapping(value = {"/films" , "film/list"})
    public String getFilms(Model model) {
        model.addAttribute("films", filmRepository.findAll());
        return "film/list";
    }

    @GetMapping
    @RequestMapping("/film/{id}/show")
    public String getFilmDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("film", filmRepository.findById(id).get());
        return "film/show";
    }

    @GetMapping
    @RequestMapping("/film/{id}/delete")
    public String deleteFilm(@PathVariable("id") Long id) {
        filmRepository.deleteById(id);
        return "redirect:/films";
    }


    @GetMapping
    @RequestMapping("/film/new")
    public String newFilm(Model model){
        model.addAttribute("film", new FilmCommand());
        model.addAttribute("director", directorRepository.findAll());
        model.addAttribute("actors", actorsRepository.findAll());
        return "film/addedit";
    }

    @GetMapping
    @RequestMapping("/film/{id}/edit")
    public String editFilm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("film", filmRepository.findById(id).get());
        return "film/addedit";
    }

    @PostMapping("film")
    public String saveOrUpdate(@ModelAttribute FilmCommand command){
        Film detachedFilm = filmCommandToFilm.convert(command);
        Film savedFilm = filmRepository.save(detachedFilm);

        return "redirect:/film/" + savedFilm.getId() + "/show";
    }
}



