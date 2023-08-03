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

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserMapperService userMapperService;
    @Override
    public void addComment(AddCommentRequest addCommentRequest) {
        Blog blog = blogRepository.findById(addCommentRequest.getBlogId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Blog with id [%s] not found".formatted(addCommentRequest.getBlogId())
                ));
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentMapper.addCommentRequestToComment(addCommentRequest);
        comment.setUser(user);
        comment.setBlog(blog);
        comment.setCommentDate(LocalDateTime.now());

        commentRepository.save(comment);
    }
    @Override
    public List<CommentDto> getCommentsByBlogIdOrderByCommentDateDesc(Long blogId) {
        return commentMapper.commentsToCommentDtoList(
                commentRepository.findCommentsByBlog_BlogIdOrderByCommentDateDesc(blogId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Blog with id [%s] not found".formatted(blogId)
                        ))
        );
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Comment With is [%s] ot found".formatted(commentId)
                ));
        var userDto = userMapperService.userToUserDto(comment.getUser());
        var commentDto = commentMapper.commentToCommentDto(comment);
        commentDto.setUserDto(userDto);
        return commentDto;
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
