package dev.arif.blogbackend.User;

import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapperService {

    private final UserRepository userRepository;

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserImageId(user.getUserImageId());
        userDto.setRoles(
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
        return userDto;
    }

    public List<UserDto> usersToUserDtoList(List<User> users){
        if(users == null){
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>(users.size());

        for(User user :users){
            list.add(userToUserDto(user));
        }

        return list;
    }

    public User updateUserRequestToUser (UpdateUserRequest updateUserRequest){
        User user = userRepository.findById(updateUserRequest.getUserId())
                        .orElseThrow(()-> new ResourceNotFoundException(
                                "User with id [%s] is not found".formatted(updateUserRequest.getUserId())
                        ));
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setUserName(updateUserRequest.getUserName());
        return user;
    }
}
