package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Blog")
public class Blog {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long blogId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="text",nullable = false)
    private String text;

    @Column(name="blog_image_url")
    private String blogImageUrl;

    @Column(name = "subject",nullable = false)
    private String subject;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_date")
    private Date updatedDate;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}
