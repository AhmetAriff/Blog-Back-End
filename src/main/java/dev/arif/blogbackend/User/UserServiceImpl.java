package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.S3.S3Service;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final S3Service s3Service;

    private final UserRepository userRepository;

    private void checkIfUserExistOrThrow(Long userId) {
        if(!userRepository.existsUserByUserId(userId)){
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
            s3Service.putObject("user-images/%s/%s".formatted(userId,userProfileImageId),
                    file.getBytes());
        }catch (IOException e){
            throw new RuntimeException("failed to upload profile image", e);
        }
        userRepository.updateUserProfileImage(userId, userProfileImageId);
    }

    @Override
    public byte[] getUserProfileImage(Long userId) {
        var user = userRepository.findUserByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));

        if(StringUtils.isBlank(user.getUserImageId())){
            throw new ResourceNotFoundException(
                    "user with id [%s] user profile image not found".formatted(userId));
        }

        return s3Service.getObject(
                "user-images/%s/%s".formatted(userId,user.getUserImageId())
        );
    }
}
