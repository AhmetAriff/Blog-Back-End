package dev.arif.blogbackend.User;

import com.amazonaws.services.cloudwatchrum.model.UserDetails;
import dev.arif.blogbackend.Blog.Blog;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_image_url", nullable = false)
    private String userImageUrl;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;

    //TODO         implements UserDetails    role based authentication


}
