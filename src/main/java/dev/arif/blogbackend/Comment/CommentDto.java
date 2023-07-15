package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.User.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
public class CommentDto {
    private User user;
    private Long commentId;
    private String comment;
    private Date commentDate;
}
