package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
}
