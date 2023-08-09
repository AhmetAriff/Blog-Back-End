package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Blog.BlogRepository;
import dev.arif.blogbackend.Blog.BlogService;
import dev.arif.blogbackend.Comment.CommentRepository;
import dev.arif.blogbackend.Comment.CommentService;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.Reply.ReplyRepository;
import dev.arif.blogbackend.Reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;
    private final BlogRepository blogRepository;

    @Override
    public void deleteBlog(Long blogId) {
        var blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Blog with id [%s] is not found"
                ));
        blogRepository.delete(blog);
    }

    @Override
    public void deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Comment with id [%s] is not found"
                ));
        commentRepository.delete(comment);
    }

    @Override
    public void deleteReply(Long replyId) {
        var reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reply with id [%s] is not found"
                ));
        replyRepository.delete(reply);
    }

    @Override
    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id [%s] is not found"
                ));
        userRepository.delete(user);
    }
    @Override
    public void giveAdminRole(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "User with id [%s] is not found"
                ));
        user.setRole(Role.ADMIN);
    }
}
