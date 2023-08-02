package ru.javamentor.springbootflywaydemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class FilmControllerIntegrationTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private FilmService filmService;
//
//    @Test
//    public void testGetListOfProviders() {
//        FilmsParametersDto filmsParametrsDto = new FilmsParametersDto();
//        List<Film> expectedFilmList = Arrays.asList(
//                new Film(1L, 1, "Film 1", 2021, 8.5, "Description 1"),
//                new Film(2L, 2, "Film 2", 2022, 7.5, "Description 2")
//        );
//
//        // Мокируем вызов сервисного класса
//        when(filmService.fetchAndSaveFilms(filmsParametrsDto)).thenReturn(expectedFilmList);
//
//        // Выполняем HTTP-запрос к контроллеру
//        ResponseEntity<List<Film>> response = restTemplate.exchange(
//                "/api/film",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Film>>() {
//                }
//        );
//
//        // Проверяем код ответа
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Проверяем возвращенные данные
//        List<Film> actualFilmList = response.getBody();
//        assertEquals(expectedFilmList.size(), actualFilmList.size());
//        assertEquals(expectedFilmList.get(0).getFilmName(), actualFilmList.get(0).getFilmName());
//        assertEquals(expectedFilmList.get(1).getFilmName(), actualFilmList.get(1).getFilmName());
//
//        // Проверяем вызов сервисного класса
//        verify(filmService, times(1)).fetchAndSaveFilms(filmsParametrsDto);
//    }
//}
