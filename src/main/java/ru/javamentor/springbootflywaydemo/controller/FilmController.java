package ru.javamentor.springbootflywaydemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootflywaydemo.CustomException.CustomExceptionOne;
import ru.javamentor.springbootflywaydemo.CustomException.CustomExceptionTwo;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;
import ru.javamentor.springbootflywaydemo.service.FilmService;

import java.util.List;


@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping("/serchFilmsOfDb")
    public ResponseEntity<List<Film>> getFilmsOfParam(FilmsParametersDto filmsParametersDto, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        try {
            if (pageSize != 0) {
                List<Film> filmList = filmService.getFilmsByParameters(filmsParametersDto, Pageable.ofSize(pageSize));
                return ResponseEntity.ok(filmList);
            } else {
                throw new CustomExceptionOne(" ноль нельзя ");
            }
        } catch (CustomExceptionOne ex) {
            System.err.println(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @GetMapping
    public ResponseEntity<List<Film>> getListOfProviders(FilmsParametersDto filmsParametrsDto) {
        try {
            List<Film> filmList = filmService.fetchAndSaveFilms(filmsParametrsDto);
            return ResponseEntity.ok(filmList);
        } catch (CustomExceptionOne ex) {
            throw ex;
        }
    }

    @PostMapping(value = "/sendReport", consumes = "application/json")
    public void sendReport(@RequestBody List<Film> filmList) {
        try {
            filmService.mailSender(filmList);
        } catch (CustomExceptionTwo ex) {
            throw ex;
        }
    }
}
