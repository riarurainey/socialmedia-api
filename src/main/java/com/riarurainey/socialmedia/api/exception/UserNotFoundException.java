package com.riarurainey.socialmedia.api.exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userData) {
        super(String.format("User with username/email %s not found", userData));
    }

    public UserNotFoundException(Long id) {
        super(String.format("User with id %s not found", id));
    }
}
