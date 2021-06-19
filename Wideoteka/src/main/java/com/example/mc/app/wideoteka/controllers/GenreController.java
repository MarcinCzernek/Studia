package com.example.mc.app.wideoteka.controllers;

import com.example.mc.app.wideoteka.command.GenreCommand;
import com.example.mc.app.wideoteka.converters.GenreCommandToGenre;
import com.example.mc.app.wideoteka.model.Genre;
import com.example.mc.app.wideoteka.repositories.ActorsRepository;
import com.example.mc.app.wideoteka.repositories.DirectorRepository;
import com.example.mc.app.wideoteka.repositories.FilmRepository;
import com.example.mc.app.wideoteka.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GenreController {

    private ActorsRepository actorsRepository;
    private DirectorRepository directorRepository;
    private FilmRepository filmRepository;
    private GenreRepository genreRepository;
    private GenreCommandToGenre genreCommandToGenre;

    public GenreController(ActorsRepository actorsRepository, DirectorRepository directorRepository, FilmRepository filmRepository, GenreRepository genreRepository, GenreCommandToGenre genreCommandToGenre) {
        this.actorsRepository = actorsRepository;
        this.directorRepository = directorRepository;
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.genreCommandToGenre = genreCommandToGenre;
    }

    @GetMapping
    @RequestMapping(value = {"/genre" , "genre/list"})
    public String getGenre(Model model) {
        model.addAttribute("genre", genreRepository.findAll());
        return "genre/list";
    }

    @GetMapping
    @RequestMapping("/genre/{id}/show")
    public String getGenreDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genre", genreRepository.findById(id).get());
        return "genre/show";
    }

    @RequestMapping("/genre/{id}/edit")
    public String editGenre(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genre", genreRepository.findById(id).get());
        return "genre/addedit";
    }

    @GetMapping
    @RequestMapping("/genre/{id}/delete")
    public String deleteGenre(@PathVariable("id") Long id) {
        genreRepository.deleteById(id);
        return "redirect:/genre";
    }

    @GetMapping
    @RequestMapping("/genre/new")
    public String newSong(Model model){
        model.addAttribute("genre", new GenreCommand());
        model.addAttribute("film", filmRepository.findAll());
        model.addAttribute("director", directorRepository.findAll());
        return "genre/addedit";
    }

    @PostMapping("genre")
    public String saveOrUpdate(@ModelAttribute GenreCommand command){
        Genre detachedGenre = genreCommandToGenre.convert(command);
        Genre savedGenre = genreRepository.save(detachedGenre);

        return "redirect:/genre/" + savedGenre.getId() + "/show";
    }
}
