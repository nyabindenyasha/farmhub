package com.xplug.tech.crop.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toSet;

@ControllerAdvice(annotations = {RestController.class})
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(CustomConstraintViolationException.class)
    public final ResponseEntity<ErrorMessage> handleCustomConstraintViolationException(CustomConstraintViolationException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(String.join(", ", ex.getConstraintViolations().stream()
                        .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                        .collect(toSet())))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ErrorMessage> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalAccessException(IllegalAccessException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalOperationException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ErrorMessage> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalStateException(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    //HttpMessageNotReadableException

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
