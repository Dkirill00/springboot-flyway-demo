package ru.javamentor.springbootflywaydemo.CustomException;

public class ReportSendingException extends RuntimeException {
    public ReportSendingException(String message) {
        super(message);
    }
}