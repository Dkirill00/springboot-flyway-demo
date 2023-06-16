package ru.javamentor.springbootflywaydemo.model;

import lombok.Data;

@Data
public class FilmDto {
    private Integer kinopoiskId;
    private String nameOriginal;
    private int year;
    private double ratingImdb;
    private String type;

}
