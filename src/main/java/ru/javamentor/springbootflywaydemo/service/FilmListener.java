package ru.javamentor.springbootflywaydemo.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmListener {
    private final FilmService filmService;

    Logger log = LoggerFactory.getLogger(FilmListener.class);

    @JmsListener(destination = "${jms.queue}")
    public void receiveFilmList(List<Film> filmList){
        log.info("Done");
        filmService.mailSender(filmList);

        try {
            filmService.mailSender(filmList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
