package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ApplicationException {

    public UserAlreadyExistException(String userData) {
        super(HttpStatus.CONFLICT, String.format("%s is already taken. Please enter a different username/email", userData));
    }

}