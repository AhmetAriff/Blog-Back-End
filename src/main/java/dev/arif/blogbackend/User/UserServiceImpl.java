package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Blog.Blog;
import dev.arif.blogbackend.Exception.DuplicateResourceException;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.Exception.TokenExpiredException;
import dev.arif.blogbackend.Register.VerificationToken.VerificationToken;
import dev.arif.blogbackend.Register.VerificationToken.VerificationTokenRepository;
import dev.arif.blogbackend.Register.UserRegistrationRequest;
import dev.arif.blogbackend.S3.S3Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final S3Service s3Service;

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapperService userMapperService;

    private void checkIfUserExistOrThrow(Long userId) {
        if (!userRepository.existsUserByUserId(userId)) {
            throw new ResourceNotFoundException(
                    "user with id [%s] not found".formatted(userId)
            );
        }
    }

    @Override
    public void uploadUserProfileImage(Long userId, MultipartFile file) {
        checkIfUserExistOrThrow(userId);
        String userProfileImageId = UUID.randomUUID().toString();
        try {
            s3Service.putObject("user-images/%s/%s".formatted(userId, userProfileImageId),
                    file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("failed to upload profile image", e);
        }
        userRepository.updateUserProfileImage(userId, userProfileImageId);
    }

    @Override
    public byte[] getUserProfileImage(Long userId) {
        var user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));

        if (StringUtils.isBlank(user.getUserImageId())) {
            throw new ResourceNotFoundException(
                    "user with id [%s] user profile image not found".formatted(userId));
        }

        return s3Service.getObject(
                "user-images/%s/%s".formatted(userId, user.getUserImageId())
        );
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Not found [%s] verification token".formatted(token)
                ));
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);
            throw new TokenExpiredException();
        }
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public User registerUser(UserRegistrationRequest request) {
        if (userRepository.existsUserByUserName(request.getUserName()))
            throw new DuplicateResourceException(
                    "username [%s] already exist".formatted(request.getUserName())
            );
        if (userRepository.existsUserByMail(request.getMail()))
            throw new DuplicateResourceException(
                    "mail [%s] already exist".formatted(request.getMail())
            );

        User user = new User();
        user.setUserName(request.getUserName());
        user.setMail(request.getMail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        userRepository.save(
                userMapperService.updateUserRequestToUser(updateUserRequest)
        );
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] is not found".formatted(userId)
                ));
        var currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.equals(user.getUserId(), currentUser.getUserId()))
            userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapperService.usersToUserDtoList(
                userRepository.findAll()
        );
    }
}
