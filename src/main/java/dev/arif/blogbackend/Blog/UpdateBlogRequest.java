package dev.arif.blogbackend.Blog;

import lombok.Data;

@Data
public class UpdateBlogRequest {
    private Long blogId;
    private String title;
    private String text;
    private String subject;

}
