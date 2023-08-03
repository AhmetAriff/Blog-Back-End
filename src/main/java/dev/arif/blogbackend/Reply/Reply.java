package dev.arif.blogbackend.Reply;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Reply")
public class Reply {

    @Id
    @SequenceGenerator(
            name = "reply_id_seq",
            sequenceName = "reply_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reply_id_seq"
    )
    private Long replyId;

    @Column(name = "reply")
    private String reply;

    @Column(name = "reply_date")
    private LocalDateTime replyDate;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;
}
