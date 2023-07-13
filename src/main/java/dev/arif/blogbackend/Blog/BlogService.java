package dev.arif.blogbackend.Blog;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
public interface BlogService {
    byte[] getBlogImage(Long blogId);

    void uploadBlogImage(Long blogId, MultipartFile file);
}
