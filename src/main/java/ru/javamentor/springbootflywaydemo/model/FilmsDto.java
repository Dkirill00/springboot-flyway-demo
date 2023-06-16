package ru.javamentor.springbootflywaydemo.model;

import lombok.Data;

import java.util.List;
@Data
public class FilmsDto {
    private Long total;
    private Long totalPages;
    private List<Film> items;

}
