package dev.arif.blogbackend.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank(message = "userName can not be blank")
    private String userName;
    @NotBlank(message = "mail can not be blank")
    @Email(message = "invalid mail form")
    private String mail;
    @NotBlank(message = "password can not be blank")
    @Size(min = 8,max = 16,message = "must be at least 8 and maximum 16 characters")
    private String password;
}
