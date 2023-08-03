package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment addCommentRequestToComment(AddCommentRequest addCommentRequest);

    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentsToCommentDtoList(List<Comment> comments);
}
