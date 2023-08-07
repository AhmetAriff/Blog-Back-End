package dev.arif.blogbackend.Reply;

import dev.arif.blogbackend.User.UserMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyMapperService {
    private final UserMapperService userMapperService;

    public Reply createReplyRequestToReply(CreateReplyRequest createReplyRequest) {
        if (createReplyRequest == null) {
            return null;
        }

        Reply reply = new Reply();

        reply.setReply(createReplyRequest.getReply());
        return reply;
    }

    public ReplyDto replyToReplyDto(Reply reply) {
        if (reply == null) {
            return null;
        }

        ReplyDto replyDto = new ReplyDto();

        replyDto.setReply(reply.getReply());
        replyDto.setReplyDate(reply.getReplyDate());
        replyDto.setUserDto(userMapperService.userToUserDto(reply.getUser()));

        return replyDto;
    }

    public List<ReplyDto> repliesToReplyDtoList(List<Reply> replies) {
        if (replies == null) {
            return null;
        }

        List<ReplyDto> list = new ArrayList<ReplyDto>(replies.size());
        for (Reply reply : replies) {
            list.add(replyToReplyDto(reply));
        }

        return list;
    }
}
