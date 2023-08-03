package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommentDto {
    private UserDto userDto;
    private Long commentId;
    private String comment;
    private LocalDateTime commentDate;
}
