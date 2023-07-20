package ru.javamentor.springbootflywaydemo.service;

import com.opencsv.CSVWriter;
import jakarta.activation.DataHandler;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.javamentor.springbootflywaydemo.config.MailConfigProperties;
import ru.javamentor.springbootflywaydemo.dto.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.model.Film;
import ru.javamentor.springbootflywaydemo.repository.FilmRepository;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class FilmService {
    private JmsTemplate jmsTemplate;
    private final FilmRepository filmRepository;
    private final KinoExchangeClient kinoExchangeClient;
    private final MailConfigProperties mailCfg;

    public void save(List<Film> films) {
        for (Film film : films) {
            if (!filmRepository.existsByFilmId(film.getFilmId())) {
                filmRepository.save(film);
            }
        }
    }

    public List<Film> fetchAndSaveFilms(FilmsParametersDto filmsParametrsDto) {
        List<Film> list = kinoExchangeClient.getFilms(filmsParametrsDto);
        save(list);
        return list;
    }

//    public List<Film> findAll(FilmsParametersDto filmsParametrsDto) {
//        Specification<Film> specification = Specification.where(null);
//
//        if (filmsParametrsDto.getGenre() != null) {
//            specification = specification.and(FilmSpecifications.hasGenre(filmsParametrsDto.getGenre()));
//        }
//        if (filmsParametrsDto.getYear() != null) {
//            specification = specification.and(FilmSpecifications.hasYear(filmsParametrsDto.getYear()));
//        }
//        if (filmsParametrsDto.getRating() != null) {
//            specification = specification.and(FilmSpecifications.hasRating(filmsParametrsDto.getRating()));
//        }
//
//        Pageable pageable = PageRequest.of(filmsParametrsDto.getPage(), filmsParametrsDto.getSize());
//
//        return filmRepository.findAll(specification, pageable).getContent();
//    }

    public List<Film> getFilmsByParameters(FilmsParametersDto filmsParametersDto, Pageable pageable) {
        Integer ratingFrom = filmsParametersDto.getRatingFrom();
        Integer ratingTo = filmsParametersDto.getRatingTo();
        Integer yearFrom = filmsParametersDto.getYearFrom();
        Integer yearTo = filmsParametersDto.getYearTo();
        String keyword = filmsParametersDto.getKeyword();
        Integer page = filmsParametersDto.getPage();

        if (ratingFrom == null) {
            ratingFrom = 0;
        }
        if (ratingTo == null) {
            ratingTo = 10;
        }
        if (yearFrom == null) {
            yearFrom = 1000;
        }
        if (yearTo == null) {
            yearTo = 3000;
        }
        if (keyword == null) {
            keyword = "";
        }
        if (page == null || page < 0) {
            page = 0;
        }
        return filmRepository.findByParameters(ratingFrom, ratingTo, yearFrom, yearTo, keyword, pageable);
    }

    public void sendFilmsViaArtemisMQ(List<Film> films){
        //Отправляем список фильмов через ArtemisMQ
        jmsTemplate.convertAndSend("filmQueue",films);
    }


    public void mailSender(List<Film> films) {

        Properties properties = new Properties();
        properties.put(mailCfg.getMailSmtpHost(), mailCfg.getHost());
        properties.put(mailCfg.getMailSmtpPort(), mailCfg.getSmtpPort());
        properties.put(mailCfg.getMailSmtpSsl(), "true");
        properties.put(mailCfg.getMailSmtpAuth(), "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailCfg.getFrom(), mailCfg.getPassword());
            }
        });
        session.setDebug(true);
        try (StringWriter writer = new StringWriter()) {
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeNext(new String[]{"film_id", "film_name", "year", "rating", "description"});
            for (Film film : films) {
                csvWriter.writeNext(new String[]{String.valueOf(film.getFilmId()), film.getFilmName(), String.valueOf(film.getYear()), String.valueOf(film.getRating()), film.getDescription()});
            }
            csvWriter.close();
            String report = writer.toString();

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailCfg.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailCfg.getTo()));
            message.setSubject(mailCfg.getSubject());
            message.setText(mailCfg.getText());

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(report.getBytes(), "text/csv")));
            attachmentPart.setFileName("film_report.csv");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
