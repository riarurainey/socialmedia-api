package com.riarurainey.socialmedia.api.activityfeed;


import com.riarurainey.socialmedia.api.post.Post;
import com.riarurainey.socialmedia.api.post.PostResponseDto;
import com.riarurainey.socialmedia.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ActivityFeedService {
    private final ActivityFeedRepository activityFeedRepository;

    public Page<PostResponseDto> getUserActivity(User user, Pageable pageable) {
        List<User> subscriptions = user.getSubscribers();
        Page<Post> page = activityFeedRepository.findByUserInOrderByCreatedAt(subscriptions, pageable);

        List<PostResponseDto> postDtos = new ArrayList<>();
        for (Post post : page.getContent()) {
            PostResponseDto postDto = convertToDto(post);
            postDtos.add(postDto);
        }

        return new PageImpl<>(postDtos, pageable, page.getTotalElements());
    }

    private PostResponseDto convertToDto(Post post) {
        return PostResponseDto.builder()
                .title(post.getTitle())
                .text(post.getText())
                .photoUrl(post.getPhotoUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
