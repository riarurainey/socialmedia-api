package com.riarurainey.socialmedia.api.post;

import com.riarurainey.socialmedia.api.exception.PostNotFoundException;
import com.riarurainey.socialmedia.api.exception.UnauthorizedException;
import com.riarurainey.socialmedia.api.user.User;
import com.riarurainey.socialmedia.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostResponseDto createPost(PostRequestDto postRequest, Long userId) {

        var currentUser = getCurrentUserById(userId);
        var post = Post.builder()
                .text(postRequest.getText())
                .title(postRequest.getTitle())
                .photoUrl(postRequest.getPhotoUrl())
                .createdAt(LocalDateTime.now())
                .user(currentUser)
                .build();
        postRepository.save(post);
        return PostResponseDto.builder()
                .text(post.getText())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .photoUrl(post.getPhotoUrl()).build();

    }

    public PostResponseDto getPost(Long postId) {

        var post = getPostById(postId);
        return PostResponseDto.builder()
                .text(post.getText())
                .title(post.getTitle())
                .photoUrl(post.getPhotoUrl())
                .createdAt(post.getCreatedAt())
                .build();

    }

    public PostResponseDto update(Long postId, PostRequestDto postRequest, Long userId) {

        var post = getPostById(postId);
        if (!Objects.equals(post.getUser().getId(), userId)) {
            throw new UnauthorizedException();
        }

        post.setText(postRequest.getText());
        post.setTitle(postRequest.getTitle());
        post.setPhotoUrl(postRequest.getPhotoUrl());

        postRepository.save(post);
        return PostResponseDto.builder()
                .text(post.getText())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .photoUrl(post.getPhotoUrl()).build();
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
    }

    private User getCurrentUserById(Long userId) {
        return userService.findById(userId);
    }

    public void deletePost(Long postId, User user) {
        var post = getPostById(postId);
        if (!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new UnauthorizedException();
        }

        postRepository.delete(post);
    }
}
