package dev.arif.blogbackend.Reply;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/replies")
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping
    ResponseEntity<?> addReply(@RequestBody CreateReplyRequest createReplyRequest){
        replyService.addReply(createReplyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("comments/{commentId}")
    ResponseEntity<List<ReplyDto>> getRepliesByCommentId(@PathVariable Long commentId){
        return ResponseEntity.ok(replyService.getRepliesByCommentId(commentId));
    }
    @DeleteMapping("{replyId}")
    ResponseEntity<?> deleteReplyById(@PathVariable Long replyId){
        replyService.deleteReply(replyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
