package com.riarurainey.socialmedia.api.activiryfeed;

import com.riarurainey.socialmedia.api.post.PostDto;
import com.riarurainey.socialmedia.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ActivityFeedRepository extends PagingAndSortingRepository<PostDto, Long> {
    Page<PostDto> findByUserInOrderByCreatedAtDesc(List<User> users, Pageable pageable);
}
