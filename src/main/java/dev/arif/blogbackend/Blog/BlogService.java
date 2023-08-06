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
    List<BlogDto> getBlogsByUser();
    List<BlogDto> getBlogsOrderByLike();
    List<BlogDto> getBlogsByUserLike(Long userId);// todo userin beğendiği blogları getiren service
    void changeLikeRate(Long blogId);
    void updateBlog(UpdateBlogRequest updateBlogRequest);
    void deleteBlog(Long blogId);

}
