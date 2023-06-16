package ru.javamentor.springbootflywaydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootflywaydemo.model.Film;
import ru.javamentor.springbootflywaydemo.model.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.service.FilmService;
import ru.javamentor.springbootflywaydemo.service.KinoExchangeClient;

import javax.naming.Binding;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @RequestMapping
    public List<Film> getListOfProviders(FilmsParametersDto filmsParametrsDto){
        return filmService.findAll(filmsParametrsDto);
    }

    @GetMapping("/sendReport")
    public void sendReport( FilmsParametersDto filmsParametrsDto){
        List<Film> films = filmService.findAll(filmsParametrsDto);
        filmService.mailSender(films);
    }


//    @GetMapping("/fill-descriptions")
//    public void fillFilmsDescriptions(@RequestParam List<Integer> movieIds){
//
//        Map<Integer, String> filmsDescriptions = filmService.getFilmsDescriptions(movieIds);
//        filmService.saveFilmsDescriptions(filmsDescriptions);
//    }
}
