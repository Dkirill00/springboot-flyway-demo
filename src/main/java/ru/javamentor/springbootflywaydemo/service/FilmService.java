package ru.javamentor.springbootflywaydemo.service;

import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javamentor.springbootflywaydemo.model.Film;
import ru.javamentor.springbootflywaydemo.model.FilmsParametersDto;
import ru.javamentor.springbootflywaydemo.repository.FilmRepository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private KinoExchangeClient kinoExchangeClient;

    public void saveFilm(Film film){
       if(!filmRepository.existsByFilmId(film.getFilmId())){
            filmRepository.save(film);
        }
    }

    public void saveAll(List<Film> films){
        for (Film film:films) {
            saveFilm(film);
        }
    }

    public List<Film> findAll(FilmsParametersDto filmsParametrsDto){
        List<Film> list = kinoExchangeClient.getFilms(filmsParametrsDto);
        saveAll(list);
        return list;
    }

    public void mailSender(List<Film> films){
        String from = "test192rs@gmail.com";
        String to = "test192rs@gmail.com";
        String password = "kpaawbgmelbnwjna";
        String host = "smtp.gmail.com";
        String smtpPort = "465";


        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password);
            }
        });
        session.setDebug(true);
        try {
//            File file = new File("film_report.csv");
//            FileWriter writer = new FileWriter(file);
            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeNext( new String[]{"film_id", "film_name", "year", "rating", "description"});
            for(Film film : films){
                csvWriter.writeNext( new String[]{String.valueOf(film.getFilmId()), film.getFilmName(), String.valueOf(film.getYear()), String.valueOf(film.getRating()), film.getDescription()});
            }
            csvWriter.close();
            String report = writer.toString();


            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Film report");
            message.setText("Please find attached film report");

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setContent(report,"text/csv");
            attachmentPart.setFileName("film_report.csv");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    public Map<Integer, String> getFilmsDescriptions(List<Integer> movieIds){
//        return kinoExchangeClient.getFilmsDescriptions(movieIds);
//    }

//    public void saveFilmsDescriptions(Map<Integer,String> filmsDescriptions) {
//        for (Integer movieId : filmsDescriptions.keySet()) {
//            Optional<Film> optionalFilm = filmRepository.findById(Long.valueOf(movieId));
//            if (optionalFilm.isPresent()) {
//                Film film = optionalFilm.get();
//                film.setDescription(filmsDescriptions.get(movieId));
//                filmRepository.save(film);
//            }
//        }
//    }
}
