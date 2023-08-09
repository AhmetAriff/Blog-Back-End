package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBlogRequest {
    @NotBlank(message = "blogId can not be blank")
    private Long blogId;
    @NotBlank(message = "title can not be blank")
    private String title;
    @NotBlank(message = "text can not be blank")
    private String text;
    private Subject subject;

}
