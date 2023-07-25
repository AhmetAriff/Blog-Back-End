package dev.arif.blogbackend.Auth;

import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String mail;
    private String password;
}
