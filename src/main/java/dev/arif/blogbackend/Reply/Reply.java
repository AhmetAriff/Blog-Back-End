package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
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

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
