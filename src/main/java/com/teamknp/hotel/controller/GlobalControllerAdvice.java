package com.teamknp.hotel.controller;

import com.teamknp.hotel.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({ResourceNotFoundException.class, NullPointerException.class})
    public String handleResourceNotFoundException() {
        return "/error/404";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException() {
        return "/error/403";
    }

}

