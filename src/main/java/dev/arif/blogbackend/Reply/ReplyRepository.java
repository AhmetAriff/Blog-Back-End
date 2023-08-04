package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {

     List<Reply> findRepliesByComment(Comment comment);
}
