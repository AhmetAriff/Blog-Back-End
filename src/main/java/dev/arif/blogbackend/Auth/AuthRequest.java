package dev.arif.blogbackend.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    @NotNull(message = "mail can not be null")
    @Email(message = "invalid mail format")
    private String mail;
    @NotNull(message = "password can not be null")
    private String password;
}
