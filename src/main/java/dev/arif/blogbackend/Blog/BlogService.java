package dev.arif.blogbackend.Blog;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface BlogService {
    byte[] getBlogImage(Long blogId);
    void uploadBlogImage(Long blogId, MultipartFile file);
    void addBlog(CreateBlogRequest createBlogRequest);
    List<BlogDto> getBlogsOrderByCreatedDate();
    List<BlogDto> getBlogsBySubject(Long subjectId);
    List<BlogDto> getBlogsByUser(Long userId);
    List<BlogDto> getBlogsOrderByLike();
    List<BlogDto> getBlogsByUserLike(Long userId);
    void changeLikeRate(Long blogId);
    void updateBlog(Long blogId,UpdateBlogRequest updateBlogRequest);
    void deleteBlog(Long blogId);

}
