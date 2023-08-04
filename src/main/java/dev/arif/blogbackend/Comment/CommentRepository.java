package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByBlogOrderByCommentDateDesc(Blog blog);

}
