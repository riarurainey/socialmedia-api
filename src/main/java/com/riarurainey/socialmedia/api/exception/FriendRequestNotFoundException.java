package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpStatus;

public class FriendRequestNotFoundException extends ApplicationException {

    public FriendRequestNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Friend request not found");
    }
}
