package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {
    @NotBlank(message = "title can not be null")
    private String title;
    @NotBlank(message = "text can not be null")
    private String text;
    private Long subjectId;
}
