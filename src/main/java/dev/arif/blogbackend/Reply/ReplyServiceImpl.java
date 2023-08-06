package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.Comment.CommentRepository;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final ReplyMapperService replyMapperService;
    @Override
    public void addReply(CreateReplyRequest createReplyRequest) {
        Comment comment = commentRepository.findById(createReplyRequest.getCommentId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Comment with id [%s] not found".formatted(createReplyRequest.getCommentId())
                ));
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Reply reply = replyMapperService.createReplyRequestToReply(createReplyRequest);
        reply.setUser(user);
        reply.setComment(comment);
        reply.setReplyDate(LocalDateTime.now());

        replyRepository.save(reply);
    }

    @Override
    public List<ReplyDto> getRepliesByCommentId (Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Comment with id [%s] id is not found".formatted(commentId)
                ));
        return replyMapperService.repliesToReplyDtoList(
                replyRepository.findRepliesByComment(comment)
        );
    }

    @Override
    public void deleteReply(Long replyId) {
    var reply = replyRepository.findById(replyId)
            .orElseThrow(()-> new ResourceNotFoundException(
                    "Reply with id [%s] not found".formatted(replyId)
            ));
    if(reply.getUser() == (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        replyRepository.delete(reply);// userlar sadece kendi yanıtlarını silebilir
    }
}
