package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Blog.BlogRepository;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserMapperService;
import dev.arif.blogbackend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final BlogRepository blogRepository;
    private final CommentMapperService commentMapperService;
    private final CommentRepository commentRepository;
    @Override
    public void addComment(AddCommentRequest addCommentRequest) {
        Blog blog = blogRepository.findById(addCommentRequest.getBlogId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Blog with id [%s] not found".formatted(addCommentRequest.getBlogId())
                ));
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentMapperService.addCommentRequestToComment(addCommentRequest);
        comment.setUser(user);
        comment.setBlog(blog);
        comment.setCommentDate(LocalDateTime.now());

        commentRepository.save(comment);
    }
    @Override
    public List<CommentDto> getCommentsByBlogIdOrderByCommentDateDesc(Long blogId) {

        var blog = blogRepository.findById(blogId)
                .orElseThrow(()-> new ResourceNotFoundException(
                                "blog with id [%s] is not found".formatted(blogId)
                ));
        return commentMapperService.commentsToCommentDtoList(
                commentRepository.findCommentsByBlogOrderByCommentDateDesc(blog)
        );
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return commentMapperService.commentToCommentDto(
                commentRepository.findById(commentId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Comment with id [%s] not found".formatted(commentId)
                        ))
        );
    }

    @Override
    public void deleteCommentsByCommentId(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "comment with id [%s] is not found".formatted(commentId)
                ));
        if(comment.getUser() == (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            commentRepository.delete(comment);// her user sadece kendi yorumunu silebilir.
    }
}
