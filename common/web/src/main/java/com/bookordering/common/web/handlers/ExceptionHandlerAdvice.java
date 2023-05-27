package com.bookordering.common.web.handlers;

import com.bookordering.common.web.transport.dto.error.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @Autowired
    private Logger logger;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = ex.getBindingResult().getAllErrors().stream()
                .map(err -> new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ((FieldError) err).getField() + " " + err.getDefaultMessage()))
                .collect(Collectors.toList());
        return errors;
    }

    //thrown by database layer if applied
    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handle(ValidationException ex) {
        if (ex instanceof ConstraintViolationException) {
            return ((ConstraintViolationException) ex)
                    .getConstraintViolations().stream().map(e -> new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage()))
                    .collect(Collectors.toList());
        }
        return List.of(new ErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handle(Exception ex) {
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
    }
}
