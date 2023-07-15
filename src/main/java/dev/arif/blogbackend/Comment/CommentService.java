package dev.arif.blogbackend.Comment;

import java.util.List;

public interface CommentService {

    void addComment (AddCommentRequest addCommentRequest);

    List<CommentDto> getCommentsByBlogId(Long blogId);

    void deleteCommentsByCommentId(Long commentId);
}
