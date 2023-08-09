package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.Jwt.Token.Token;
import dev.arif.blogbackend.Reply.Reply;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UpdateUserRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
}
