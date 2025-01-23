package com.xplug.tech.config;

import com.xplug.tech.exception.*;
import com.xplug.tech.notification.NotificationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toSet;

//@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ControllerAdvice(basePackageClasses = {NotificationController.class})
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

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public final ResponseEntity<ErrorMessage> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex, WebRequest request) {
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

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {
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

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleRecordNotFoundException(ItemNotFoundException ex, WebRequest request) {
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
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleIllegalStateException(MethodArgumentNotValidException ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleIllegalStateException(Exception ex, WebRequest request) {
        ErrorMessage errorDetails = ErrorMessage.builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .exception(ex.getClass().getName())
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
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
