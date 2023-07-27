package dev.arif.blogbackend.Register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @Email(message = "invalid mail form")
    @NotBlank(message = "mail can not be null")
    private String mail;
    @NotBlank(message = "username can not be null")
    private String userName;
    @NotBlank(message = "password can not be null")
    @Size(min = 8,max = 16,message = "must be at least 8 and maximum 16 characters")
    private String password;
}
