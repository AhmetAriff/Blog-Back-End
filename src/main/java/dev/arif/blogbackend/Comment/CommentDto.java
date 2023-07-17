package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.User.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDto {
    private User user;
    private Long commentId;
    private String comment;
    private LocalDateTime commentDate;
}
