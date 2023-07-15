package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import lombok.Data;

@Data
public class CreateReplyRequest {
    private String reply;
    private User user;
    private Comment comment;
}
