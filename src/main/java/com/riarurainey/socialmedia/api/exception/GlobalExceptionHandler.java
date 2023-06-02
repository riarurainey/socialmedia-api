package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        var apiError =
                new ApiError(HttpStatus.BAD_REQUEST, "Validation error", errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }


    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(ApplicationException ex) {
        var apiError = ApiError.builder()
                .status(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}