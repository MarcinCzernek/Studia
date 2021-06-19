package com.example.mc.app.wideoteka.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilmCommand {
    private Long id;
    private String title;
    private String genre;
    private String director;
    private String year;
    private String actors;
    private String writer;
    private Long ActorsId;
    private Long DirectorId;


}
