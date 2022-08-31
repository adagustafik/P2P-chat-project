package com.gfa.week22p2pchatproject.controller;

import com.gfa.week22p2pchatproject.exception.LoginUnsuccessfulException;
import com.gfa.week22p2pchatproject.exception.NameAlreadyTakenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(NameAlreadyTakenException.class)
    public String handleHttpClientErrorException(NameAlreadyTakenException exception,
                                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/register";
    }

    @ExceptionHandler(LoginUnsuccessfulException.class)
    public String handleHttpClientErrorException(LoginUnsuccessfulException exception,
                                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/login";
    }
}
