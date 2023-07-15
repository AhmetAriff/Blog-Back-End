package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.Reply.Reply;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_image_id", nullable = false)
    private String userImageId;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    @OneToMany(mappedBy="user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Reply> replies;


    //TODO         implements UserDetails   , role based authentication


}
