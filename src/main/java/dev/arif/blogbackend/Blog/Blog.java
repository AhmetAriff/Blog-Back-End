package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.Subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Blog")
public class Blog {

    @Id
    @SequenceGenerator(
            name="blog_id_seq",
            sequenceName = "blog_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blog_id_seq"
    )
    private Long blogId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name="text",nullable = false)
    private String text;

    @Column(name="blog_image_id")
    private String blogImageId;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(mappedBy = "likedBlogs")
    Set<User> likes;

}
