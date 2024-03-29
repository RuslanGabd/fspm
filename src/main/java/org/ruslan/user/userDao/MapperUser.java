package org.ruslan.user.userDao;


import lombok.AllArgsConstructor;
import org.ruslan.user.userEntity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MapperUser {

    private final PasswordEncoder passwordEncoder;

    public UserDto userToDto(User user) {
        return new UserDto(user.getUsername(),
                user.getPassword());
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
         return user;
    }

}
