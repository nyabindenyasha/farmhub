package com.xplug.tech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Error> handleRecordNotFoundException(RecordNotFoundException ex) {
        Error errorDetails = Error.builder()
                .message(ex.getMessage())
                .exception(ex.getClass().getName())
                .httpStatusCode(HttpStatus.NOT_FOUND.value())
                .build();
        ex.printStackTrace();
        logger.warn(RecordNotFoundException.class);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Error> handleInvalidRequestException(InvalidRequestException ex) {
        Error errorDetails = Error.builder()
                .message(ex.getMessage())
                .exception(ex.getClass().getName())
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        ex.printStackTrace();
        logger.warn(InvalidRequestException.class);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemErrorException.class)
    public final ResponseEntity<Error> handleSystemErrorException(SystemErrorException ex) {
        Error errorDetails = Error.builder()
                .message(ex.getMessage())
                .exception(ex.getClass().getName())
                .httpStatusCode(HttpStatus.I_AM_A_TEAPOT.value())
                .build();
        ex.printStackTrace();
        logger.error(SystemErrorException.class);
        return new ResponseEntity<>(errorDetails, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(RecordAlreadyExistException.class)
    public final ResponseEntity<Error> handleSystemErrorException(RecordAlreadyExistException ex) {
        Error errorDetails = Error.builder()
                .message(ex.getMessage())
                .exception(ex.getClass().getName())
                .httpStatusCode(HttpStatus.FORBIDDEN.value())
                .build();
        ex.printStackTrace();
        logger.warn(RecordNotFoundException.class);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

}
