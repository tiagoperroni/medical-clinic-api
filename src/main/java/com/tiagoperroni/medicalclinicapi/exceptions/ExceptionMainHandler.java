package com.tiagoperroni.medicalclinicapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionMainHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardMessage> entityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new StandardMessage(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()),
                        LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardMessage> validData(ConstraintViolationException ex) {
       List<String> messages = ex.getConstraintViolations().stream().map(msg -> msg.getMessage()).collect(Collectors.toList());
       return new ResponseEntity<>(
               new StandardMessage(HttpStatus.BAD_REQUEST.value(), messages, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardMessage> cpfDuplicated(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(
                new StandardMessage(HttpStatus.BAD_REQUEST.value(), List.of("O CPF já está cadastrado na base de dados."),
                        LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
