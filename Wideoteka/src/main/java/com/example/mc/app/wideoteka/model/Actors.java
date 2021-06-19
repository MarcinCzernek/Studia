package com.example.mc.app.wideoteka.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    private Set<Film> films = new HashSet<>();

    public Actors(String lastName, Set<Film> films) {
        this.lastName = lastName;
        this.films = films;
    }

    public Actors() {
    }

    public Actors(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
