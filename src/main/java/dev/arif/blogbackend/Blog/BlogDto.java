package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import dev.arif.blogbackend.User.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BlogDto {

    private Long blogId;

    private String title;

    private String text;

    private String blogImageId;

    private Subject subject;

    private int likeCount; //entity sınıfımda yok sadece burda var
    // entiyiyi koyarsam @Transient anatasyonu eklemeliyim

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private User user;
}
