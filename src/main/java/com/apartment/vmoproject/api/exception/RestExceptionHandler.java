package com.apartment.vmoproject.api.exception;


import com.apartment.vmoproject.api.model.ExceptionObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    private void logException(Exception ex) {
        logger.error(ex.getMessage(), ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex){
        logException(ex);
        ExceptionObject res = ExceptionObject.builder().status(HttpStatus.NOT_FOUND.toString()).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(res);
    }
}
