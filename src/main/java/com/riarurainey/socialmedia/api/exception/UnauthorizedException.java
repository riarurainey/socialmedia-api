package com.riarurainey.socialmedia.api.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super(String.format("You are not the owner of this resource"));
    }

}