package dev.arif.blogbackend.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment addCommentRequestToComment(AddCommentRequest addCommentRequest);

    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentsToCommentDtoList(List<Comment> comments);
}
