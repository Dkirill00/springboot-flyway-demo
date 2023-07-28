package ru.javamentor.springbootflywaydemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootflywaydemo.CustomException.FilmSearchException;
import ru.javamentor.springbootflywaydemo.CustomException.ReportSendingException;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;
import ru.javamentor.springbootflywaydemo.service.FilmScheduler;
import ru.javamentor.springbootflywaydemo.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    private final FilmScheduler filmScheduler;

    @PostMapping(value = "/artemis")
    public ResponseEntity<List<Film>> send() {
        filmScheduler.fetchAndSendFilmsByGenre();
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/serchFilmsOfDb")
//    public ResponseEntity<List<Film>> getFilmsOfParam(FilmsParametersDto filmsParametersDto, @RequestParam(required = false, defaultValue = "10") int pageSize) {
//        try {
//            Pageable pageable;
//            if(pageSize <= 0) {
//                pageable = Pageable.unpaged();
//            } else {
//                pageable = Pageable.ofSize(pageSize);
//            }
//            List<Film> filmList = filmService.getFilmsByParameters(filmsParametersDto, pageable);
//
//            if (filmList.isEmpty()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            return ResponseEntity.ok(filmList);
//        } catch (FilmSearchException ex) {
//            System.err.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }

    @GetMapping("/serchFilmsOfDb")
    public ResponseEntity<List<Film>> getFilmsOfParam(FilmsParametersDto filmsParametersDto, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        try {
            Pageable pageable;
            if(pageSize <= 0) {
                pageable = Pageable.unpaged();
            } else {
                pageable = Pageable.ofSize(pageSize);
            }
            List<Film> filmList = filmService.getFilmsByParameters(filmsParametersDto, pageable);
            return ResponseEntity.ok(filmList);
        } catch (FilmSearchException ex) {
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Film>> getListOfProviders(FilmsParametersDto filmsParametrsDto) {
        try {
            List<Film> filmList = filmService.fetchAndSaveFilms(filmsParametrsDto);
            return ResponseEntity.ok(filmList);
        } catch (FilmSearchException ex) {
            throw ex;
        }
    }

    @PostMapping(value = "/sendReport", consumes = "application/json")
    public void sendReport(@RequestBody List<Film> filmList) {
        try {
            filmService.mailSender(filmList);
        } catch (ReportSendingException ex) {
            throw ex;
        }
    }
}
