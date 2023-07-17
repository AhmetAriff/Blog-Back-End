package dev.arif.blogbackend.Reply;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateReplyRequest {
    @NotBlank(message = "reply can not be null")
    private String reply;
    private Long userId;
    private Long commentId;
}
