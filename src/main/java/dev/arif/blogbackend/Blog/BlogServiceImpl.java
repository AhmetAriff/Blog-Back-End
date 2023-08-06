package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.S3.S3Service;
import dev.arif.blogbackend.Subject.Subject;
import dev.arif.blogbackend.Subject.SubjectRepository;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@RequiredArgsConstructor
@Service
@Transactional
public class BlogServiceImpl implements BlogService{

    private final S3Service s3Service;
    private final BlogRepository blogRepository;
    private final SubjectRepository subjectRepository;
    private final BlogMapperService blogMapperService;
    private final UserRepository userRepository;

    private void checkIfBlogExistOrThrow(Long blogId) {
        if(!blogRepository.existsBlogByBlogId(blogId)){
            throw new ResourceNotFoundException(
                    "blog with id [%s] not found".formatted(blogId)
            );
        }
    }
    @Override
    public void uploadBlogImage(Long blogId, MultipartFile file) {
        checkIfBlogExistOrThrow(blogId);
        String blogImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject("blog-images/%s/%s".formatted(blogId,blogImageId),
            file.getBytes());
        }catch (IOException e){
            throw new RuntimeException("failed to upload blog image", e);
        }
        blogRepository.updateBlogImage(blogId, blogImageId);
    }

    @Override
    public void addBlog(CreateBlogRequest createBlogRequest) {
        Subject subject = subjectRepository.findById(createBlogRequest.getSubjectId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Subject with id [%s] not found".formatted(createBlogRequest.getSubjectId())
                ));
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Blog blog = blogMapperService.createBlogRequestToBlog(createBlogRequest);
        blog.setSubject(subject);
        blog.setUser(user);
        blog.setCreatedDate(LocalDateTime.now());

        blogRepository.save(blog);
    }

    @Override
    public List<BlogDto> getBlogsOrderByCreatedDate() {
        return blogMapperService.blogsToBlogDtoList(
                blogRepository.findAllByOrderByCreatedDateDesc()
        );
    }

    @Override
    public List<BlogDto> getBlogsBySubject(Long subjectId) {
        var subject = subjectRepository.findSubjectBySubjectId(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "subject with id [%s] is not found".formatted(subjectId)
                ));
        return blogMapperService.blogsToBlogDtoList(
                blogRepository.findBlogsBySubject(subject)
        );
    }
    @Override
    public List<BlogDto> getBlogsByUser() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return blogMapperService.blogsToBlogDtoList(
                blogRepository.findBlogsByUser(user)
        );
    }

    @Override
    public List<BlogDto> getBlogsOrderByLike() {
       return blogMapperService.blogsToBlogDtoList(
               blogRepository.findAllOrderByLikesDesc()
       );
    }

    @Override
    public List<BlogDto> getBlogsByUserLike(Long userId) {
        return blogMapperService.blogsToBlogDtoList(
                blogRepository.findDistinctByLikes_UserId(userId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "User with [%s] id not found".formatted(userId)
                        ))
        );
    }

    @Override
    public void changeLikeRate(Long blogId) {
        var blog = blogRepository.findBlogByBlogId(blogId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Blog with [%s] id is not found".formatted(blogId)
                ));
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getLikedBlogs().contains(blog)){
            user.getLikedBlogs().remove(blog);
            userRepository.save(user);
        }
        else {
            user.getLikedBlogs().add(blog);
            userRepository.save(user);
        }
    }

    @Override
    public void updateBlog(UpdateBlogRequest updateBlogRequest) {
         blogRepository.save(
                 blogMapperService.updateBlogRequestToBlog(
                         blogRepository.findBlogByBlogId(updateBlogRequest.getBlogId())
                                 .orElseThrow(()-> new ResourceNotFoundException(
                                         "Blog with [%s] id is not found".formatted(updateBlogRequest.getBlogId())
                                 )),updateBlogRequest
                 )
         );
    }

    @Override
    public void deleteBlog(Long blogId) {
        var blog = blogRepository.findBlogByBlogId(blogId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "blog with id [%s] is not found".formatted(blogId)
                ));
        if (blog.getUser() == (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            blogRepository.delete(blog);// userlar sadece kendi bloglarını silebilir
    }

    @Override
    public byte[] getBlogImage(Long blogId) {
        var blog = blogRepository.findBlogByBlogId(blogId)
                .orElseThrow(()-> new  ResourceNotFoundException(
                        "blog with id [%s] not found".formatted(blogId)
                ));


        if (StringUtils.isBlank(blog.getBlogImageId())) {
            throw new ResourceNotFoundException(
                    "blog with id [%s] blog image not found".formatted(blogId));
        }

        return s3Service.getObject(
                "blog-images/%s/%s".formatted(blogId,blog.getBlogImageId())
        );
    }

}
