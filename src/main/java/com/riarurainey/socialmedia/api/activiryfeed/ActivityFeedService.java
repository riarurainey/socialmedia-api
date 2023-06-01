package com.riarurainey.socialmedia.api.activiryfeed;


import com.riarurainey.socialmedia.api.post.PostDto;
import com.riarurainey.socialmedia.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityFeedService {
    private final ActivityFeedRepository activityFeedRepository;

    public Page<PostDto> getUserActivity(User user, Pageable pageable) {
        List<User> subscriptions = user.getSubscribers();
        return activityFeedRepository.findByUserInOrderByCreatedAtDesc(subscriptions, pageable);
    }
}
