package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Register.UserRegistrationRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void uploadUserProfileImage(Long userId, MultipartFile file);

    byte[] getUserProfileImage(Long userId);

    void saveUserVerificationToken(User user, String token);

    String validateVerificationToken(String token);

    User registerUser(UserRegistrationRequest request);
}
