package com.riarurainey.socialmedia.api.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException() {
        super("Post not found");
    }

}