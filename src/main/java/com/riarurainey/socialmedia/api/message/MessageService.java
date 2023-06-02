package com.riarurainey.socialmedia.api.message;

import com.riarurainey.socialmedia.api.exception.FriendshipNotFoundException;
import com.riarurainey.socialmedia.api.user.User;
import com.riarurainey.socialmedia.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserService userService;
    private final MessageRepository messageRepository;

    public void sendMessage(Long userId, MessageRequestDto messageRequest) {
        User sender = userService.findById(userId);

        User receiver = userService.findById(messageRequest.getReceiverId());


        if (receiver.getFriends().contains(sender) && sender.getFriends().contains(receiver)) {
            Message message = Message.builder()
                    .sender(sender)
                    .receiver(receiver)
                    .content(messageRequest.getContent())
                    .createdAt(LocalDateTime.now())
                    .build();

            messageRepository.save(message);
        } else {
            throw new FriendshipNotFoundException(sender.getId(), receiver.getId());
        }
    }

}
