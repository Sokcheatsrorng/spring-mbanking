package co.istad.sokcheatmbankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResolutionException.class)
    ResponseEntity<?> handleServiceErrors(ResponseStatusException ex)
    {
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error",ex.getReason()));
    }



}
