package dev.arif.blogbackend.Auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String mail;
    private String password;
}
