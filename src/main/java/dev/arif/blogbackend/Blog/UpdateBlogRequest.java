package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    private Long blogId;
    private String title;
    private String text;
    private Subject subject;

}
