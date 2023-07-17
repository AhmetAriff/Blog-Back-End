package dev.arif.blogbackend.Comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    ResponseEntity<?> addComment(@RequestBody @Valid AddCommentRequest addCommentRequest){
        commentService.addComment(addCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("{commentId}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.getCommentById(commentId));
    }
    @GetMapping("blogs/{blogId}")
    ResponseEntity<List<CommentDto>> getCommentsByBlogId(@PathVariable Long blogId){
        return ResponseEntity.ok(commentService.getCommentsByBlogId(blogId));
    }
    @DeleteMapping("{commentId}")
    ResponseEntity<?> deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentsByCommentId(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
