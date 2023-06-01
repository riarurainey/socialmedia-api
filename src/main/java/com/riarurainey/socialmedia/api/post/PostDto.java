package com.riarurainey.socialmedia.api.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String title;
    private String text;
    private String photoUrl;
    private LocalDateTime createdAt;

}
