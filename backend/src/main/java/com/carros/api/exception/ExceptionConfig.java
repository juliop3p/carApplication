  
package com.carros.api.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.models.Response;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    public ResponseEntity<Response> errorNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
           IllegalArgumentException.class
    })
    public ResponseEntity<Response>  errorBadRequest() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({
            AccessDeniedException.class
    })
    public ResponseEntity<Error>  accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado"));
    }
}

class Error {
    public String error;

    public Error(String error) {
        this.error = error;
    }
}