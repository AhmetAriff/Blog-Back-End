package dev.arif.blogbackend.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Reply.Reply;
import dev.arif.blogbackend.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {

    @Id
    @SequenceGenerator(
            name="comment_id_seq",
            sequenceName = "comment_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_id_seq"
    )
    private Long commentId;

    @Column(name = "comment",nullable = false)
    private String comment;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
