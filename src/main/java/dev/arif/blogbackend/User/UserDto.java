package dev.arif.blogbackend.User;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private List<String> roles;
    private String userImageId;
}
