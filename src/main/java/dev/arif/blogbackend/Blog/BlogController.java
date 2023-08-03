package dev.arif.blogbackend.Blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;


    @PostMapping
    public ResponseEntity<?> addBlog(@RequestBody CreateBlogRequest createBlogRequest) {
        blogService.addBlog(createBlogRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BlogDto>> getBlogsOrderByCreatedDate() {
        return ResponseEntity.ok(blogService.getBlogsOrderByCreatedDate()) ;
    }

    @PostMapping(
            value = "{blogId}/blog-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadBlogImage(
            @PathVariable("blogId") Long BlogId,
            @RequestParam("file")MultipartFile file) {
         blogService.uploadBlogImage(BlogId,file) ;
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
