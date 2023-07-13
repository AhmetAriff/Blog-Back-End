package dev.arif.blogbackend.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    @PostMapping(
            value = ("{userId}/profile-image"),
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void uploadUserProfileImage(
            @PathVariable("userId") Long userId,
            @RequestParam("file")MultipartFile file){
        userService.uploadUserProfileImage(userId,file);
    }
    @GetMapping(
            value = ("{userId}/profile-image"),
            consumes = MediaType.IMAGE_JPEG_VALUE
    )
    public byte[] getUserProfileImage(
            @PathVariable("blogId") Long userId){
        return userService.getUserProfileImage(userId);
    }

}
