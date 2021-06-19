package com.example.mc.app.wideoteka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String genre;
    private String director;
    private String year;
    private String actors;
    private String writer;

    @ManyToMany (mappedBy = "films")
    private Set<Director> directors = new HashSet<>();

    public Film() {
    }

    public Film(String title, String genre, String director, String year, String actors, String writer) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.actors = actors;
        this.writer = writer;
    }

    public void setActors(Actors actors) {
    }

    public void setActors(String actors) {
    }
}
