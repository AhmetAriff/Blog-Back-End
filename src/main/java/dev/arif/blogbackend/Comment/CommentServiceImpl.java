package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Blog.BlogRepository;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    @Override
    public void addComment(AddCommentRequest addCommentRequest) {
        User user = userRepository.findUserByUserId(addCommentRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "User with id [%s] not found".formatted(addCommentRequest.getUserId())
                ));
        Blog blog = blogRepository.findById(addCommentRequest.getBlogId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Blog with id [%s] not found".formatted(addCommentRequest.getBlogId())
                ));
        Comment comment = commentMapper.addCommentRequestToComment(addCommentRequest);
        comment.setUser(user);
        comment.setBlog(blog);

        commentRepository.save(comment);
    }
    @Override
    public List<CommentDto> getCommentsByBlogId(Long blogId) {
        return commentMapper.commentsToCommentDtoList(
                commentRepository.findCommentsByBlog_BlogId(blogId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Blog with id [%s] not found".formatted(blogId)
                        ))
        );
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return commentMapper.commentToCommentDto(
                commentRepository.findById(commentId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Comment With is [%s] ot found".formatted(commentId)
                        ))
        );
    }

    @Override
    public void deleteCommentsByCommentId(Long commentId) {
        commentRepository.delete(
                commentRepository.findById(commentId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Comment with id [%s] not found".formatted(commentId)
                        ))
        );
    }
}
