package dev.arif.blogbackend.Comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddCommentRequest {
    @NotBlank(message = "comment can not be null")
    private String comment;
    private Long blogId;
    private Long userId;
}
