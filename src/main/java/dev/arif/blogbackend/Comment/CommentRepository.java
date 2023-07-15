package dev.arif.blogbackend.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<List<Comment>> findCommentsByBlog_BlogId(Long blogId);

}
