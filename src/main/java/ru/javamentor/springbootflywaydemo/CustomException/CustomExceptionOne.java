package ru.javamentor.springbootflywaydemo.CustomException;

public class CustomExceptionOne extends RuntimeException{
    public CustomExceptionOne(String message) {
        super(message);
    }
}