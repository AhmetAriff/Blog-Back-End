package dev.arif.blogbackend.Reply;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Override
    public void addReply(CreateReplyRequest createReplyRequest) {

    }

    @Override
    public List<ReplyDto> getRepliesByCommentId(Long commentId) {
        return null;
    }

    @Override
    public void deleteReply(Long replyId) {

    }
}
