package com.example.tourweb.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException exception, Model model) {
        model.addAttribute("message",exception.getMessage());
        return "exception";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidExceptions(
            MethodArgumentNotValidException ex, Model model) {
        model.addAttribute("message",ex.getFieldError().getDefaultMessage());
        return "exception";
    }
}
