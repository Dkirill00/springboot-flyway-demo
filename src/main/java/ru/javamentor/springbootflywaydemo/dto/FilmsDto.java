package ru.javamentor.springbootflywaydemo.dto;

import lombok.Data;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.List;
@Data
public class FilmsDto {
    private Long total;
    private Long totalPages;
    private List<Film> items;
}
