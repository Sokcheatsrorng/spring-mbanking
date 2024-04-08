package co.istad.sokcheatmbankingapi.exception;

import co.istad.sokcheatmbankingapi.base.BasedError;
import co.istad.sokcheatmbankingapi.base.BasedErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class MediaException {
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSize;
    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public Map<String, Object> handleMediaErrors(MaxUploadSizeExceededException ex) {
//        return Map.of("error",ex.getMessage());

//    }
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    BasedErrorResponse handleMediaErrors(MaxUploadSizeExceededException ex)
    {
        BasedError<String> basedError =  BasedError.<String>builder()
                .code(HttpStatus.PAYLOAD_TOO_LARGE.getReasonPhrase())
                .description("Media upload size maximum is: "+maxSize)
                .build();
        return new BasedErrorResponse(basedError);
    }
}
