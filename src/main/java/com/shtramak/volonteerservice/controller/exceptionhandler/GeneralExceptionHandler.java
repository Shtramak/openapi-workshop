package com.shtramak.volonteerservice.controller.exceptionhandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shtramak.volonteerservice.exception.InvalidDonationAmountException;
import lombok.Value;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException exception) {
        return new ErrorResponse(LocalDateTime.now(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDonationAmountException.class)
    public ErrorResponse handleInvalidDonationAmountException(InvalidDonationAmountException exception) {
        return new ErrorResponse(LocalDateTime.now(), exception.getMessage());
    }

    @Value
    static class ErrorResponse {

        LocalDateTime dateTime;
        String message;
    }
}
