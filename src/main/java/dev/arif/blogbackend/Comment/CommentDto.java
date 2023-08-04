package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Reply.ReplyDto;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto {
    private Long commentId;
    private String comment;
    private LocalDateTime commentDate;
    private UserDto userDto;
}
