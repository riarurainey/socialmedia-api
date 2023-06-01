package com.riarurainey.socialmedia.api.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String userData) {
        super(String.format("%s is already taken. Please enter a different username/email", userData));
    }

}