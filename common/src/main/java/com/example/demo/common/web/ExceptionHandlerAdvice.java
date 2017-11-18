package com.example.demo.common.web;

import com.example.demo.common.Mapper;
import com.example.demo.common.dto.ValidationErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final Mapper mapper;

    public ExceptionHandlerAdvice(Mapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception ex, Locale locale) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(mapper.map(ex, locale), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationError(MethodArgumentNotValidException ex) {
        log.error("Validation error:", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();
        for (FieldError fieldError : fieldErrors) {
            validationErrorDto
                    .addError(fieldError.getObjectName(), fieldError.getField(), fieldError.getCodes()[0], fieldError
                            .getDefaultMessage());

        }
        return ResponseEntity.badRequest().body(validationErrorDto);

    }
}
