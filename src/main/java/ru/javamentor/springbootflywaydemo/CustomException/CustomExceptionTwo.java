package ru.javamentor.springbootflywaydemo.CustomException;

public class CustomExceptionTwo  extends RuntimeException {
    public CustomExceptionTwo(String message) {
        super(message);
    }
}