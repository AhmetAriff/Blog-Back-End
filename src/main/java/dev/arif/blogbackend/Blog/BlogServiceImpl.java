package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.S3.S3Service;
import dev.arif.blogbackend.Subject.Subject;
import dev.arif.blogbackend.Subject.SubjectRepository;
import dev.arif.blogbackend.User.User;
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
    private final BlogMapper blogMapper;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) authentication.getPrincipal();
        Blog blog = blogMapper.createBlogRequestToBlog(createBlogRequest);
        blog.setSubject(subject);
        blog.setUser(user);
        blog.setCreatedDate(LocalDateTime.now());

        blogRepository.save(blog);
    }

    @Override
    public List<BlogDto> getBlogsOrderByCreatedDate() {
        return blogMapper.blogsToBlogDtoList(
                blogRepository.findAllByOrderByCreatedDateDesc()
        );
    }

    @Override
    public List<BlogDto> getBlogsBySubject(Long subjectId) {
        return blogMapper.blogsToBlogDtoList(
                blogRepository.findBlogsBySubject_SubjectId(subjectId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "Subject with [%s] is not found".formatted(subjectId)
                        ))
        );
    }
    @Override
    public List<BlogDto> getBlogsByUser(Long userId) {
        return blogMapper.blogsToBlogDtoList(
                blogRepository.findBlogByUser_UserId(userId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "User with [%s] id not found".formatted(userId)
                        ))
        );
    }

    @Override
    public List<BlogDto> getBlogsOrderByLike(Long userId) {
       return blogMapper.blogsToBlogDtoList(
               blogRepository.findAllOrderByLikesDesc()
       );
    }

    @Override
    public List<BlogDto> getBlogsByUserLike(Long userId) {
        return blogMapper.blogsToBlogDtoList(
                blogRepository.findDistinctByLikes_UserId(userId)
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "User with [%s] id not found".formatted(userId)
                        ))
        );
    }

    @Override
    public void updateBlog(UpdateBlogRequest updateBlogRequest) {
         blogRepository.save(
                 blogMapper.updateBlogRequestToBlog(
                         blogRepository.findBlogByBlogId(updateBlogRequest.getBlogId())
                                 .orElseThrow(()-> new ResourceNotFoundException(
                                         "Blog with [%s] is is not found".formatted(updateBlogRequest.getBlogId())
                                 )),updateBlogRequest
                 )
         );
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
