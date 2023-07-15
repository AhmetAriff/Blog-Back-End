package dev.arif.blogbackend.Reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {

    Optional <List<Reply>> findRepliesByComment_CommentId(Long commentId);
}
