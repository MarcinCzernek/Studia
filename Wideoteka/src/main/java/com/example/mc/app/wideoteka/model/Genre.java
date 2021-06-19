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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany (mappedBy = "directors")
    private Set<Film> films = new HashSet<>();

    public Genre(String name) {

        this.name = name;
    }

    public Genre() {
    }



}
