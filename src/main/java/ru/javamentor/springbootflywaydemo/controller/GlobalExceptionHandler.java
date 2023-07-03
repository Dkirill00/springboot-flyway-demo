package ru.javamentor.springbootflywaydemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.javamentor.springbootflywaydemo.CustomException.CustomExceptionOne;
import ru.javamentor.springbootflywaydemo.CustomException.CustomExceptionTwo;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionOne.class)
    public ResponseEntity<String> handleCustomException1(CustomExceptionOne ex) {
        log.error("CustomExceptionOne occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(CustomExceptionTwo.class)
    public ResponseEntity<String> handleCustomException2(CustomExceptionTwo ex) {
        log.error("CustomExceptionTwo occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Unhandled exception occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}