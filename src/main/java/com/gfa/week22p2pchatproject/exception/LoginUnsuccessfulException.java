package com.gfa.week22p2pchatproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LoginUnsuccessfulException extends Exception {
    private String message;
}
