package ru.javamentor.springbootflywaydemo.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilmListener {
    private FilmService filmService;

    @JmsListener(destination = "filmQueue")
    public void receiveFilmList(List<Film> filmList){
        try {
            filmService.mailSender(filmList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
