package com.riarurainey.socialmedia.api.exception;

public class FriendshipNotFoundException extends RuntimeException {

    public FriendshipNotFoundException(Long senderId, Long receiverId) {
        super(String.format("No friendship between user with id - %d " +
                "and user with id - %d. This action is prohibited.", senderId, receiverId));
    }
}
