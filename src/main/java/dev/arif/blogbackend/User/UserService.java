package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Register.UserRegistrationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void uploadUserProfileImage(Long userId, MultipartFile file);

    byte[] getUserProfileImage(Long userId);

    void saveUserVerificationToken(User user, String token);

    boolean validateVerificationToken(String token);

    User registerUser(UserRegistrationRequest request);

    void updateUser(UpdateUserRequest updateUserRequest);

    void deleteUser(Long userId);

    List<UserDto> getAllUsers();
}
