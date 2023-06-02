package com.riarurainey.socialmedia.api.exception;

import org.springframework.http.HttpStatus;

public class FriendshipNotFoundException extends ApplicationException {

    public FriendshipNotFoundException(Long senderId, Long receiverId) {
        super(HttpStatus.NOT_FOUND, String.format("No friendship between user with id - %d " +
                "and user with id - %d. This action is prohibited.", senderId, receiverId));
    }
}
