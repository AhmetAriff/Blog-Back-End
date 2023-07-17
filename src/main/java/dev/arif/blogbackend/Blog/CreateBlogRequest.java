package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import lombok.Data;

@Data
public class CreateBlogRequest {
    private String title;
    private String text;
    private Subject subject;
}
