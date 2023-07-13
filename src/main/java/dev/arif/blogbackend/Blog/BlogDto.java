package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class BlogDto {

    private Long blogId;

    private String title;

    private String text;

    private String blogImageId;

    private String subject;

    private Integer likeCount;

    private Date createdDate;

    private Date updatedDate;

    private List<Comment> comments;

    private User user;
}
