package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import dev.arif.blogbackend.Subject.SubjectDto;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BlogDto {

    private Long blogId;

    private String title;

    private String text;

    private int likeCount; //entity sınıfımda yok sadece burda var
    // entiyiyi koyarsam @Transient anatasyonu eklemeliyim

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String blogImageId;

    private SubjectDto subjectDto;

    private UserDto userDto;
}
