package dev.arif.blogbackend.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    Optional<Blog> findBlogByBlogId(Long blogId);
    Boolean existsBlogByBlogId(Long blogId);
    @Modifying
    @Query("update Blog u set u.blogImageId = :image where u.blogId = :id")
    void updateBlogImage(@Param(value = "id") long id, @Param(value = "image") String image);
}
