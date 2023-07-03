package ru.javamentor.springbootflywaydemo.dto;

import lombok.Data;

@Data
public class FilmsParametersDto {
    private Integer ratingFrom;
    private Integer ratingTo;
    private Integer yearFrom;
    private Integer yearTo;
    private String keyword;
    private Integer page;
}
