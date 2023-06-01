package com.riarurainey.socialmedia.api.friendship;

import com.riarurainey.socialmedia.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class FriendShipController {
    private final FriendShipService friendService;

    @PostMapping("/friend-requests/{receiverId}")
    public void sendFriendShipRequest(@AuthenticationPrincipal User user, @PathVariable Long receiverId) {
        friendService.sendFriendShipRequest(user.getId(), receiverId);
    }

    @PostMapping("/friend-requests/{receiverId}/accept")
    public void acceptFriendShipRequest(@AuthenticationPrincipal User user, @PathVariable Long receiverId) {
        friendService.acceptFriendShipRequest(user.getId(), receiverId);
    }

    @PostMapping("/friend-requests/{receiverId}/reject")
    public void rejectFriendsShipRequest(@AuthenticationPrincipal User user, @PathVariable Long receiverId) {
        friendService.rejectFriendsShipRequest(user.getId(), receiverId);
    }


    @DeleteMapping("/friends/{friendId}")
    public void removeFriend(@AuthenticationPrincipal User user, @PathVariable Long friendId) {
        friendService.removeFriend(user.getId(), friendId);
    }

}

