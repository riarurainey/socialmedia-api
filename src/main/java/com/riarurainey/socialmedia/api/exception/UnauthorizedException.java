package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "You are not the owner of this resource");
    }

}