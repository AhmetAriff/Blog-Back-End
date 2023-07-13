package dev.arif.blogbackend.Blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

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
