package dev.arif.blogbackend.Comment;

import java.util.List;

public interface CommentService {

    void addComment (AddCommentRequest addCommentRequest);

    List<CommentDto> getCommentsByBlogIdOrderByCommentDateDesc(Long blogId);

    CommentDto getCommentById(Long commentId);

    void deleteCommentsByCommentId(Long commentId);
}
