package dev.arif.blogbackend.Auth;

import dev.arif.blogbackend.Jwt.JWTUtil;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserDto;
import dev.arif.blogbackend.User.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthResponse login (AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getPassword()
                )
        );
        User principal = (User) authentication.getPrincipal();
        UserDto userDto = userMapper.userToUserDto(principal);
        String token = jwtUtil.issueToken(userDto.getUserName(),userDto.getRoles());
        return new AuthResponse(token,userDto);
    }
}
