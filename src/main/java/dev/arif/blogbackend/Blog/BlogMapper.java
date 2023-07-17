package dev.arif.blogbackend.Blog;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogMapper {

    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);

    Blog createBlogRequestToBlog(CreateBlogRequest createBlogRequest);
    BlogDto blogToBlogDto(Blog blog);
    List<BlogDto> blogsToBlogDtoList(List<Blog> blogs);
    Blog updateBlogRequestToBlog(@MappingTarget Blog blog, UpdateBlogRequest updateBlogRequest);
}
