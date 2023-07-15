package dev.arif.blogbackend.Reply;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReplyMapper {

    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    Reply createReplyRequestToReply(CreateReplyRequest createReplyRequest);

    ReplyDto replyToReplyDto(Reply reply);

    List<ReplyDto> repliesToReplyDtoList(List<Reply> replies);
}
