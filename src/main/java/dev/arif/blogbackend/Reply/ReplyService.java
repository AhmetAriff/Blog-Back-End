package dev.arif.blogbackend.Reply;

import java.util.List;

public interface ReplyService {

    void addReply(CreateReplyRequest createReplyRequest);

    List<ReplyDto> getRepliesByCommentId(Long commentId);

    void deleteReply(Long replyId);
}
