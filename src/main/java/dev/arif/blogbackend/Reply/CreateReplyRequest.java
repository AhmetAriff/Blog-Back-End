package dev.arif.blogbackend.Reply;

import lombok.Data;

@Data
public class CreateReplyRequest {
    private String reply;
    private Long userId;
    private Long commentId;
}
