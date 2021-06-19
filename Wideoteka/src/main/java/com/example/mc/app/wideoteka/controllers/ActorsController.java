package com.example.mc.app.wideoteka.controllers;

import com.example.mc.app.wideoteka.command.ActorsCommand;
import com.example.mc.app.wideoteka.converters.ActorsCommandToActors;
import com.example.mc.app.wideoteka.model.Actors;
import com.example.mc.app.wideoteka.repositories.ActorsRepository;
import com.example.mc.app.wideoteka.repositories.DirectorRepository;
import com.example.mc.app.wideoteka.repositories.FilmRepository;
import com.example.mc.app.wideoteka.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class ActorsController {

    private ActorsRepository actorsRepository;
    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;
    private ActorsCommandToActors actorsCommandToActors;

    public ActorsController(ActorsRepository actorsRepository, DirectorRepository directorRepository, FilmRepository filmRepository, GenreRepository genreRepository, ActorsCommandToActors actorsCommandToActors) {
        this.actorsRepository = actorsRepository;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.actorsCommandToActors = actorsCommandToActors;
    }

    @RequestMapping(value = {"/actors", "/actors/list"})
    public String getActors(Model model) {
        model.addAttribute("actors", actorsRepository.findAll());
        return "actors/list";
    }

    @RequestMapping("/actors/{id}/films")
    public String getActorsFilms(Model model, @PathVariable("id") Long id) {
        Optional<Actors> actors = actorsRepository.findById(id);

        if (actors.isPresent()) {
            model.addAttribute("film", filmRepository.getAllByActorsIsContaining(actors.get()));
            model.addAttribute("filter", "actors: " + actors.get().getFirstName() + " " + actors.get().getLastName());
        } else {
            model.addAttribute("film", new ArrayList<>());
            model.addAttribute("filter", "actors for this id doesn't exist");
        }

        return "actors/list";
    }

    @RequestMapping("/actors/{id}/show")
    public String getActorsDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("actors", actorsRepository.findById(id).get());
        return "actors/show";
    }

    @RequestMapping("/actors/{id}/edit")
    public String editActors(Model model, @PathVariable("id") Long id) {
        model.addAttribute("actors", actorsRepository.findById(id).get());
        return "actors/addedit";
    }


    @RequestMapping("/actors/{id}/delete")
    public String deleteActors(@PathVariable("id") Long id) {
        actorsRepository.deleteById(id);
        return "redirect:/actors";
    }

    @GetMapping
    @RequestMapping("/actors/new")
    public String newFilm(Model model){
        model.addAttribute("actors", new ActorsCommand());
        return "actors/addedit";
    }


    @PostMapping("actors")
    public String saveOrUpdate(@ModelAttribute ActorsCommand command){
        Optional<Actors> actorsOptional = actorsRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!actorsOptional.isPresent()) {
            Actors detachedActors = actorsCommandToActors.convert(command);
            Actors savedActors = actorsRepository.save(detachedActors);
            return "redirect:/actors/" + savedActors.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such actor in db");
            return "redirect:/actors/" + actorsOptional.get().getId() + "/show";
        }
    }
}


