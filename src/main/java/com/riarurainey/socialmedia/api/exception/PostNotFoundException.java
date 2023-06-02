package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ApplicationException {

    public PostNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Post not found");
    }

}