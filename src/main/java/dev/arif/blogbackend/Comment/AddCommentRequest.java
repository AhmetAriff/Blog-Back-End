package dev.arif.blogbackend.Comment;

import lombok.Data;

@Data
public class AddCommentRequest {
    private String comment;
    private Long blogId;
    private Long userId;
}
