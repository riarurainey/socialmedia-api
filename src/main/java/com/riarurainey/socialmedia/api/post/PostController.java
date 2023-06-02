package com.riarurainey.socialmedia.api.post;

import com.riarurainey.socialmedia.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequest,
                                                      @AuthenticationPrincipal User user) {
        var postResponse = postService.createPost(postRequest, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long postId,
                                                  @RequestBody PostRequestDto postRequest,
                                                  @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(postService.update(postId, postRequest, user.getId()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId,
                                       @AuthenticationPrincipal User user) {
        postService.deletePost(postId, user);
        return ResponseEntity.noContent().build();

    }
}