package dev.arif.blogbackend.User;

public interface AdminService {
    void deleteBlog(Long blogId);
    void deleteComment(Long commentId);
    void deleteReply(Long replyId);
    void deleteUser(Long userId);
}
