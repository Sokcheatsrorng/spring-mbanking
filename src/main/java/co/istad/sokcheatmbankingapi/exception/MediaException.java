package co.istad.sokcheatmbankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.Map;

@RestControllerAdvice
public class MediaException {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map<String, Object> handleMediaErrors(MaxUploadSizeExceededException ex) {
        return Map.of("error",ex.getMessage());
    }
    ResponseEntity<?> handleMediaErrors(ResponseStatusException ex)
    {
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error",ex.getReason()));
    }
}
