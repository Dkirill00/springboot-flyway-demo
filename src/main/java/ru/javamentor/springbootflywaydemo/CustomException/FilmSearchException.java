package ru.javamentor.springbootflywaydemo.CustomException;

public class FilmSearchException extends RuntimeException{
    public FilmSearchException(String message) {
        super(message);
    }
}