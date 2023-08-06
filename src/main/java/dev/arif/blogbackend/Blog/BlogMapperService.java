package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.SubjectMapper;
import dev.arif.blogbackend.User.UserMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogMapperService {
    private final SubjectMapper subjectMapper;
    private final UserMapperService userMapperService;
    
    public Blog createBlogRequestToBlog(CreateBlogRequest createBlogRequest) {
        if ( createBlogRequest == null ) {
            return null;
        }
        Blog blog = new Blog();
        
        blog.setTitle( createBlogRequest.getTitle() );
        blog.setText( createBlogRequest.getText() );
        return blog;
    }

    public BlogDto blogToBlogDto(Blog blog) {
        if ( blog == null ) {
            return null;
        }

        BlogDto blogDto = new BlogDto();

        blogDto.setBlogId( blog.getBlogId() );
        blogDto.setTitle( blog.getTitle() );
        blogDto.setText( blog.getText() );
        blogDto.setBlogImageId( blog.getBlogImageId() );
        blogDto.setLikeCount(blog.getLikes().size());
        blogDto.setSubjectDto( subjectMapper.subjectToSubjectDto(blog.getSubject()) );
        blogDto.setCreatedDate( blog.getCreatedDate() );
        blogDto.setUpdatedDate( blog.getUpdatedDate() );
        blogDto.setUserDto( userMapperService.userToUserDto(blog.getUser()));

        return blogDto;
    }

    public List<BlogDto> blogsToBlogDtoList(List<Blog> blogs) {
        if ( blogs == null ) {
            return null;
        }

        List<BlogDto> list = new ArrayList<BlogDto>( blogs.size() );
        for ( Blog blog : blogs ) {
            list.add( blogToBlogDto( blog ) );
        }

        return list;
    }

    public Blog updateBlogRequestToBlog(Blog blog, UpdateBlogRequest updateBlogRequest) {
        if ( updateBlogRequest == null ) {
            return blog;
        }

        blog.setBlogId( updateBlogRequest.getBlogId() );
        blog.setTitle( updateBlogRequest.getTitle() );
        blog.setText( updateBlogRequest.getText() );
        blog.setSubject( updateBlogRequest.getSubject() );

        return blog;
    }
}
