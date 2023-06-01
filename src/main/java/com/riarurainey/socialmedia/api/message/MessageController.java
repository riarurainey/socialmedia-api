package com.riarurainey.socialmedia.api.message;

import com.riarurainey.socialmedia.api.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public void sendMessage(@AuthenticationPrincipal User user, @RequestBody MessageRequestDto messageRequest) {
        messageService.sendMessage(user.getId(), messageRequest);
    }
}
