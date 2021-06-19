package com.example.mc.app.wideoteka.controllers;

import com.example.mc.app.wideoteka.command.DirectorCommand;
import com.example.mc.app.wideoteka.converters.DirectorCommandToDirector;
import com.example.mc.app.wideoteka.model.Director;
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
public class DirectorController {

    private ActorsRepository actorsRepository;
    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;
    private DirectorCommandToDirector directorCommandToDirector;

    public DirectorController(ActorsRepository actorsRepository, DirectorRepository directorRepository, FilmRepository filmRepository, GenreRepository genreRepository,DirectorCommandToDirector directorCommandToDirector) {
        this.actorsRepository = actorsRepository;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.directorCommandToDirector = directorCommandToDirector;
    }
    @RequestMapping(value = {"/director", "/director/list"})
    public String getDirectors(Model model) {
        model.addAttribute("director", directorRepository.findAll());
        return "director/list";
    }

    @RequestMapping("/director/{id}/films")
    public String getDirectorFilms(Model model, @PathVariable("id") Long id) {
        Optional<Director> director = directorRepository.findById(id);

        if (director.isPresent()) {
            model.addAttribute("film", filmRepository.getAllByDirectorIsContaining(director.get()));
            model.addAttribute("filter", "director: " + director.get().getFirstName() + " " + director.get().getLastName());
        } else {
            model.addAttribute("film", new ArrayList<>());
            model.addAttribute("filter", "director for this id doesn't exist");
        }

        return "director/list";
    }

    @RequestMapping("/director/{id}/show")
    public String getDirectorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("director", directorRepository.findById(id).get());
        return "director/show";
    }

    @RequestMapping("/director/{id}/edit")
    public String editDirector(Model model, @PathVariable("id") Long id) {
        model.addAttribute("director", directorRepository.findById(id).get());
        return "director/addedit";
    }


    @RequestMapping("/director/{id}/delete")
    public String deleteDirector(@PathVariable("id") Long id) {
        directorRepository.deleteById(id);
        return "redirect:/director";
    }

    @GetMapping
    @RequestMapping("/director/new")
    public String newFilm(Model model){
        model.addAttribute("director", new DirectorCommand());
        return "director/addedit";
    }


    @PostMapping("director")
    public String saveOrUpdate(@ModelAttribute DirectorCommand command){

        Optional<Director> directorOptional = directorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!directorOptional.isPresent()) {
            Director detachedDirector = directorCommandToDirector.convert(command);
            Director savedDirector = directorRepository.save(detachedDirector);
            return "redirect:/director/" + savedDirector.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such director in db");
            return "redirect:/director/" + directorOptional.get().getId() + "/show";
        }
    }
}





