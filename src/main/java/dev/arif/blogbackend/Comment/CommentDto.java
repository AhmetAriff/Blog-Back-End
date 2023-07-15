package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.User.User;
import jakarta.persistence.*;

public class CommentDto {
    private Long commentId;
    private String comment;
    private Blog blog;
    private User user;
}
