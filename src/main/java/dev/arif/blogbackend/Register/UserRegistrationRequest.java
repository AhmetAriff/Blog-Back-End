package dev.arif.blogbackend.Register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @Email
    @NotBlank(message = "mail can not be null")
    private String mail;
    @NotBlank(message = "username can not be null")
    private String userName;
    @NotBlank(message = "password can not be null")
    private String password;
}
