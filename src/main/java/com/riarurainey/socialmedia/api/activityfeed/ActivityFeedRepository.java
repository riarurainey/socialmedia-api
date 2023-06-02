package com.riarurainey.socialmedia.api.activityfeed;

import com.riarurainey.socialmedia.api.post.Post;
import com.riarurainey.socialmedia.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActivityFeedRepository extends PagingAndSortingRepository<Post, Long> {
    Page<Post> findByUserInOrderByCreatedAt(List<User> users, Pageable pageable);
}
