package com.riarurainey.socialmedia.api.exception;


import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(String userData) {
        super(HttpStatus.NOT_FOUND, String.format("User with username/email %s not found", userData));
    }

    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, String.format("User with id %s not found", id));
    }
}
