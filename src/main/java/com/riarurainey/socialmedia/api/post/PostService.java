package com.riarurainey.socialmedia.api.post;

import com.riarurainey.socialmedia.api.exception.PostNotFoundException;
import com.riarurainey.socialmedia.api.exception.UnauthorizedException;
import com.riarurainey.socialmedia.api.user.User;
import com.riarurainey.socialmedia.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostDto createPost(PostDto postRequest, Long userId) {

        var currentUser = getCurrentUserById(userId);
        var post = Post.builder()
                .text(postRequest.getText())
                .title(postRequest.getTitle())
                .photoUrl(postRequest.getPhotoUrl())
                .user(currentUser)
                .build();
        postRepository.save(post);
        return PostDto.builder()
                .text(post.getText())
                .title(post.getTitle())
                .photoUrl(post.getPhotoUrl()).build();

    }

    public PostDto getPost(Long postId) {
        var post = getPostById(postId);
        return PostDto.builder()
                .text(post.getText())
                .title(post.getTitle())
                .photoUrl(post.getPhotoUrl()).build();
    }

    public PostDto update(Long postId, PostDto postRequest,  Long userId) {

        var post = getPostById(postId);
        if (!Objects.equals(post.getUser().getId(), userId)) {
            throw new UnauthorizedException();
        }

        post.setText(postRequest.getText());
        post.setTitle(postRequest.getTitle());
        post.setPhotoUrl(postRequest.getPhotoUrl());

        postRepository.save(post);
        return PostDto.builder()
                .text(post.getText())
                .title(post.getTitle())
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
