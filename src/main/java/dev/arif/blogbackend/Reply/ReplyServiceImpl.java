package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.Comment.Comment;
import dev.arif.blogbackend.Comment.CommentRepository;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final ReplyMapper replyMapper;
    @Override
    public void addReply(CreateReplyRequest createReplyRequest) {

        User user = userRepository.findUserByUserId(createReplyRequest.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "User with id [%s] not found".formatted(createReplyRequest.getUserId())
                ));
        Comment comment = commentRepository.findById(createReplyRequest.getCommentId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Comment with id [%s] not found".formatted(createReplyRequest.getCommentId())
                ));
        Reply reply = replyMapper.createReplyRequestToReply(createReplyRequest);
        reply.setUser(user);
        reply.setComment(comment);
        reply.setReplyDate(LocalDateTime.now());

        replyRepository.save(reply);
    }

    @Override
    public List<ReplyDto> getRepliesByCommentId (Long commentId) {
        return replyMapper.repliesToReplyDtoList(
                replyRepository.findRepliesByComment_CommentId(commentId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Comment with id [%s] not found".formatted(commentId)
                        ))
        );
    }

    @Override
    public void deleteReply(Long replyId) {
        replyRepository.delete(
                replyRepository.findById(replyId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Reply with id [%s] not found".formatted(replyId)
                        ))
        );
    }
}
