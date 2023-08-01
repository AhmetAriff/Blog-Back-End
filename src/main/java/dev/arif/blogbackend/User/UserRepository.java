package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUserId(Long userId);

    Optional<User> findUserByUserName(String userName);
    Boolean existsUserByUserId(Long blogId);
    @Modifying
    @Query("update User u set u.userImageId = :image where u.userId = :id")
    void updateUserProfileImage(@Param(value = "id") long id, @Param(value = "image") String image);
    Optional<User> findUserByMail(String mail);
    Boolean existsUserByUserName(String userName);
    Boolean existsUserByMail(String mail);
}
