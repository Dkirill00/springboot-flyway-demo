//package ru.javamentor.springbootflywaydemo.service;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
//import ru.javamentor.springbootflywaydemo.model.Film;
//import ru.javamentor.springbootflywaydemo.repository.FilmRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class FilmServiceTest {
//    @Mock
//    private FilmRepository filmRepository;
//    @Mock
//    private KinoExchangeClient kinoExchangeClient;
//    @InjectMocks
//    private FilmService filmService;
//
//
//    @Test
//    public void save() {
//        List<Film> films = new ArrayList<>();
//        Film film1 = new Film(1L, 1, "Film 1", 2022, 8.5, "Description 1");
//        Film film2 = new Film(2L, 2, "Film 2", 2021, 7.9, "Description 2");
//        films.add(film1);
//        films.add(film2);
//
//        Mockito.when(filmRepository.existsByFilmId(Mockito.anyInt())).thenReturn(false);
//
//        filmService.save(films);
//
//        Mockito.verify(filmRepository, Mockito.times(2)).save(any(Film.class));
//    }
//
//    @Test
//    public void fetchAndSaveFilms() {
//        FilmsParametersDto filmsParametrsDto = new FilmsParametersDto();
//        filmsParametrsDto.setRatingFrom(7);
//        filmsParametrsDto.setRatingTo(9);
//        filmsParametrsDto.setYearFrom(2000);
//        filmsParametrsDto.setYearTo(2020);
//        filmsParametrsDto.setKeyword("action");
//        filmsParametrsDto.setPage(1);
//
//        List<Film> films = filmService.fetchAndSaveFilms(filmsParametrsDto);
////        films.add(new Film(1L, 2, "action", 2015, 8.5, "action"));
////        films.add(new Film(2L, 2, "film 2", 2001, 6.5, "not film"));
//
//        // Настройка мок-объекта KinoExchangeClient
//        when(kinoExchangeClient.getFilms(any(FilmsParametersDto.class))).thenReturn(films);
//
//        List<Film> result = filmService.fetchAndSaveFilms(filmsParametrsDto);
//        System.out.println(result);
//
//        assertEquals(films, result);
//        Assert.assertNotNull(result);
//
//    }
//
//    @Test
//    public void getFilmsByParameters() {
//        FilmsParametersDto filmsParametersDto = new FilmsParametersDto();
//        filmsParametersDto.setRatingFrom(7);
//        filmsParametersDto.setYearFrom(2000);
//        filmsParametersDto.setKeyword("action");
//        Pageable pageable = PageRequest.of(0, 10);
//
//        List<Film> films = new ArrayList<>();
//        films.add(new Film(1L, 1, "Film 1", 2005, 7.5, "Action film"));
//        films.add(new Film(2L, 2, "Film 2", 2003, 8.0, "Action thriller"));
//
//        // Устанавливаем поведение репозитория при вызове метода findByParameters
//        Mockito.when(filmRepository.findByParameters(7, null, 2000, null, "action", pageable)).thenReturn(films);
//
//        List<Film> result = filmService.getFilmsByParameters(filmsParametersDto, pageable);
//
//        Assert.assertEquals(films, result);
//
//        // Проверяем вызов метода findByParameters
//        Mockito.verify(filmRepository).findByParameters(7, null, 2000, null, "action", pageable);
//    }
//
//    @Test
//    public void mailSender() {
//    }
//}