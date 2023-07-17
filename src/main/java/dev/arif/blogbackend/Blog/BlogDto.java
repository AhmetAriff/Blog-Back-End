package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.User.User;
import lombok.Data;

import java.util.Date;
@Data
public class BlogDto {

    private Long blogId;

    private String title;

    private String text;

    private String blogImageId;

    private String subject;

    private int likeCount; //entity sınıfımda yok sadece bursa var
    // entiyiyi koyarsam @Transient anatasyonu eklemeliyim

    private Date createdDate;

    private Date updatedDate;

    private User user;
}
