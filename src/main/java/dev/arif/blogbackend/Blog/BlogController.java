package dev.arif.blogbackend.Blog;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;


    @PostMapping
    public ResponseEntity<?> addBlog(@Valid @RequestBody CreateBlogRequest createBlogRequest) {
        blogService.addBlog(createBlogRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("sorting")
    public ResponseEntity<List<BlogDto>> getBlogsOrderBy(@RequestParam(required = false) String sort_by) {
        if (sort_by.equalsIgnoreCase("created-date"))
            return ResponseEntity.ok(blogService.getBlogsOrderByCreatedDate());
        else if (sort_by.equalsIgnoreCase("like"))
            return ResponseEntity.ok(blogService.getBlogsOrderByLike());
        else
            throw new RuntimeException("invalid sort parameter");
    }

    @GetMapping("filtering")
    public ResponseEntity<List<BlogDto>> getBlogsFilterBy(@RequestParam(required = false) String filterBy, @RequestParam Long id) {
        if (filterBy.equalsIgnoreCase("subject"))
            return ResponseEntity.ok(blogService.getBlogsBySubject(id));
        else if (filterBy.equalsIgnoreCase("user"))
            return ResponseEntity.ok(blogService.getBlogsByUser(id));
        else if (filterBy.equalsIgnoreCase("users_like"))
            return ResponseEntity.ok(blogService.getBlogsByUserLike(id));
        else
            throw new RuntimeException("invalid filter parameter");
    }

    @PutMapping("changeLikeRate/{blogId}")
    public ResponseEntity<?> changeLikeRate(@PathVariable Long blogId) {
        blogService.changeLikeRate(blogId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{blogId}")
    public ResponseEntity<?> updateBlog(@PathVariable Long blogId, @RequestBody UpdateBlogRequest updateBlogRequest){
        blogService.updateBlog(blogId,updateBlogRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(
            value = "{blogId}/blog-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadBlogImage(
            @PathVariable("blogId") Long BlogId,
            @RequestParam("file") MultipartFile file) {
        blogService.uploadBlogImage(BlogId, file);
    }

    @GetMapping(
            value = "{blogId}/blog-image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getBlogImage(
            @PathVariable("blogId") Long BlogId) {
        return blogService.getBlogImage(BlogId);
    }
}
