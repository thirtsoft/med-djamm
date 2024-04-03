package com.meddjamm.sn.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlerDemo extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProfilAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> profilAlreadyExists(ProfilAlreadyExistsException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDto.builder()
                .httpCode(BAD_REQUEST.value())
                .message(ex.getMessage())
                .build(), BAD_REQUEST);
    }

    @ExceptionHandler(ProfilNotFoundException.class)
    public ResponseEntity<ErrorDto> profilNotFound(ProfilNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDto.builder()
                .httpCode(NOT_FOUND.value())
                .message(ex.getMessage())
                .build(), NOT_FOUND);
    }

    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ResponseEntity<ErrorDto> userNotFound(UtilisateurNotFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDto.builder()
                .httpCode(NOT_FOUND.value())
                .message(ex.getMessage())
                .build(), NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> userNotFound(BadCredentialsException ex, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDto.builder()
                .httpCode(FORBIDDEN.value())
                .message(ex.getMessage())
                .build(), FORBIDDEN);
    }
}