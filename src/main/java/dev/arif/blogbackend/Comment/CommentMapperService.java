package dev.arif.blogbackend.Comment;

import dev.arif.blogbackend.User.UserMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentMapperService {
    private final UserMapperService userMapperService;
    public Comment addCommentRequestToComment(AddCommentRequest addCommentRequest) {
        if ( addCommentRequest == null ) {
            return null;
        }
        Comment comment = new Comment();
        comment.setComment( addCommentRequest.getComment() );
        return comment;
    }

    public CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        var userDto = userMapperService.userToUserDto(comment.getUser());
        commentDto.setCommentId( comment.getCommentId() );
        commentDto.setComment( comment.getComment() );
        commentDto.setCommentDate( comment.getCommentDate() );
        commentDto.setUserDto(userDto);

        return commentDto;
    }

    public List<CommentDto> commentsToCommentDtoList(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( commentToCommentDto( comment ) );
        }

        return list;
    }


}
