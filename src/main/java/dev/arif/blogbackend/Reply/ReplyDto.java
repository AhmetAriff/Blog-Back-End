package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReplyDto {
    private Long id;
    private String reply;
    private LocalDateTime replyDate;
    private User user;

}
