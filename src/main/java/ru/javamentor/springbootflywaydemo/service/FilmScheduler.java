package ru.javamentor.springbootflywaydemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilmScheduler {
    private FilmService filmService;

    // Scheduler будет запускаться каждый день в 8 утра
    @Scheduled(cron = "0 0 8 * * ?")
    public void fetchAndSendFilmsByGenre() {
        int dayOfWeek = java.time.LocalDate.now().getDayOfWeek().getValue();

        String genre = getGenreByDayOfWeek(dayOfWeek);

        FilmsParametersDto filmsParametersDto = new FilmsParametersDto();
//        filmsParametersDto.setGenre(genre);
        filmsParametersDto.setPage(20);
        List<Film> filmList = filmService.fetchAndSaveFilms(filmsParametersDto);

        // Отправляет список фильмов через ArtemisMQ
        filmService.sendFilmsViaArtemisMQ(filmList);
    }

    private String getGenreByDayOfWeek(int dayOfWeek) {
        if (dayOfWeek == 1) {
            return "Комедия";
        } else if (dayOfWeek == 2) {
            return "Триллер";
        } else if (dayOfWeek == 3) {
            return "Драма";
        }
        return null;
    }
}