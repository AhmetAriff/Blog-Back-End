package dev.arif.blogbackend.Blog;

import dev.arif.blogbackend.Subject.Subject;
import dev.arif.blogbackend.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findBlogByBlogId(Long blogId);

    List<Blog> findBlogsBySubject(Subject subject);

    List<Blog> findAllByOrderByCreatedDateDesc();

    @Query("SELECT b FROM Blog b order by SIZE(b.likes) DESC")
    List<Blog> findAllOrderByLikesDesc();

    List<Blog> findBlogsByUser(User user);

    List<Blog> findAllByLikesContaining(User user); //TODO fonksiyon bozuk olabilir düzelt

    Boolean existsBlogByBlogId(Long blogId);

    @Modifying
    @Query("update Blog u set u.blogImageId = :image where u.blogId = :id")
    void updateBlogImage(@Param(value = "id") long id, @Param(value = "image") String image);
}
