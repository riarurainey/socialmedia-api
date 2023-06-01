package com.riarurainey.socialmedia.api.friendship;

import com.riarurainey.socialmedia.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendShipService {
    private final UserService userService;

    public void sendFriendShipRequest(Long senderId, Long receiverId) {
        var sender = userService.findById(senderId);
        var receiver = userService.findById(receiverId);

        receiver.getFriendRequests().add(sender);
        sender.getSubscribers().add(receiver);
        receiver.getSubscriptions().add(sender);
        userService.save(sender);
    }


    public void acceptFriendShipRequest(Long userId, Long requestSenderId) {
        var user = userService.findById(userId);
        var requestSender = userService.findById(requestSenderId);

        if (user.getFriendRequests().contains(requestSender)) {
            user.getFriendRequests().remove(requestSender);
            user.getFriends().add(requestSender);
            requestSender.getFriends().add(user);
            user.getSubscribers().add(requestSender);
            userService.save(user);
            userService.save(requestSender);

        }// выбросить искл, что такой заявки нет

    }

    public void rejectFriendsShipRequest(Long userId, Long requestSenderId) {
        var user = userService.findById(userId);
        var requestSender = userService.findById(requestSenderId);
        if (user.getFriendRequests().contains(requestSender)) {
            user.getFriendRequests().remove(requestSender);
            userService.save(user);

        } else {
            // выбросить искл, что такой заявки нет
        }
    }

    public void removeFriend(Long userId, Long friendId) {
        var user = userService.findById(userId);
        var friend = userService.findById(friendId);

        if (user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            friend.getFriends().remove(user);
            friend.getSubscribers().remove(user);
            user.getSubscriptions().remove(friend);
            userService.save(user);
            userService.save(friend);
        }

    }
}

