package ru.javamentor.springbootflywaydemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.javamentor.springbootflywaydemo.model.FilmsDto;
import ru.javamentor.springbootflywaydemo.model.FilmsParametersDto;

import ru.javamentor.springbootflywaydemo.model.Film;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KinoExchangeClient {
//    HttpClient httpClient = HttpClientBuilder.create().build();
//    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//    private RestTemplate restTemplate = new RestTemplate(requestFactory);

    private final RestTemplate restTemplate ;
    @Value("${api.key}")
    private String apk;
    private String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films";

    private String getUrl(FilmsParametersDto filmsParametrsDto){
        String urlComponentsBuilder = UriComponentsBuilder.fromUriString(url)
                .queryParam("ratingFrom", filmsParametrsDto.getRatingFrom())
                .queryParam("ratingTo", filmsParametrsDto.getRatingTo())
                .queryParam("yearFrom", filmsParametrsDto.getYearFrom())
                .queryParam("yearTo",filmsParametrsDto.getYearTo())
                .queryParam("keyword", filmsParametrsDto.getKeyword())
                .queryParam("page",filmsParametrsDto.getPage()).build().toUriString();
        return urlComponentsBuilder;
    }

    public List<Film> getFilms(FilmsParametersDto filmsParametrsDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-API-KEY", apk);
            HttpEntity<FilmsDto> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<FilmsDto> responseEntity = restTemplate.exchange(getUrl(filmsParametrsDto), HttpMethod.GET, httpEntity,FilmsDto.class);
            return getFilmsDescriptions(responseEntity.getBody().getItems());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public List<Film> getFilmsDescriptions(List<Film> filmList){
        for(Film film : filmList){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-API-KEY", apk);
            HttpEntity<FilmsDto> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<Film> response =  restTemplate.exchange(url+"/"+film.getFilmId(), HttpMethod.GET, httpEntity, Film.class);
            film.setDescription(response.getBody().getDescription());
        }
        return filmList;
    }
}
