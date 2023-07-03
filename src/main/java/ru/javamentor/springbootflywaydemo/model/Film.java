package ru.javamentor.springbootflywaydemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "film_id")
    @JsonProperty("kinopoiskId")
    private Integer filmId;
    @Column(name = "film_name")
    @JsonProperty("nameOriginal")
    private String filmName;
    @Column(name = "year")
    @JsonProperty("year")
    private int year;
    @Column(name = "rating")
    @JsonProperty("ratingImdb")
    private double rating;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;
}
