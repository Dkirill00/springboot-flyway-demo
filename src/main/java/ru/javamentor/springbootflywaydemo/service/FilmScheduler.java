package ru.javamentor.springbootflywaydemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class FilmScheduler {
    private final FilmService filmService;

    // Scheduler будет запускаться каждый день в 8 утра
//    @Scheduled(cron = "0 0 8 * * ?")
    @Scheduled(cron = "0 * * * * ?")
    public void fetchAndSendFilmsByGenre() {
        int dayOfWeek = java.time.LocalDate.now().getDayOfWeek().getValue();

        String genre = getGenreByDayOfWeek(dayOfWeek);

        FilmsParametersDto filmsParametersDto = new FilmsParametersDto();
//        filmsParametersDto.setKeyword("war");
        filmsParametersDto.setYearTo(3000);
        filmsParametersDto.setYearFrom(1000);
        filmsParametersDto.setPage(10);
        filmsParametersDto.setRatingTo(9);
        filmsParametersDto.setRatingFrom(2);
        filmsParametersDto.setGenres(Collections.singletonList(genre));
        Pageable pageable = PageRequest.of(10, 20);
        filmsParametersDto.setPage(20);
        List<Film> filmList = filmService.getFilmsByParameters(filmsParametersDto, pageable);

        // Отправляет список фильмов через ArtemisMQ
        filmService.sendFilmsViaArtemisMQ(filmList);
    }

    private String getGenreByDayOfWeek(int dayOfWeek) {
        if (dayOfWeek == 1) { //id 13
            return "комедия";
        } else if (dayOfWeek == 2) { //id 1
            return "триллер";
        } else if (dayOfWeek == 3) { //id 2
            return "драма";
        } else if (dayOfWeek == 4) { //id 3
            return "криминал";
        } else if (dayOfWeek == 5) { //id 6
            return "фантастика";
        } else if (dayOfWeek == 6) { //id 7
            return "приключения";
        } else if (dayOfWeek == 7) { //id 17
            return "ужасы";
        }
        return null;
    }
}