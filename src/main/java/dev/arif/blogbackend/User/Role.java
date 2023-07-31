package dev.arif.blogbackend.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    ADMIN,
    USER;
    public SimpleGrantedAuthority getAuthorities(){
        return new SimpleGrantedAuthority("ROLE_" + this.name());
    }
}
