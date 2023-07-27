package dev.arif.blogbackend.User;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void uploadUserProfileImage(Long userId, MultipartFile file);

    byte[] getUserProfileImage(Long userId);

    void saveUserVerificationToken(User user, String verificationToken);
}
