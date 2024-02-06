package org.ruslan.user;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.ruslan.web.webExceptions.UserExistException;
import org.ruslan.user.jwt.JwtDto;
import org.ruslan.user.userDao.MapperUser;
import org.ruslan.user.userDao.UserDto;
import org.ruslan.user.userEntity.User;
import org.ruslan.user.userService.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MapperUser mapperUser;

    @PostMapping("/login")
    public JwtDto login(@RequestBody UserDto userDto) {
        return new JwtDto(userService.login(mapperUser.userDtoToUser(userDto)));
    }

    @PostMapping("/registration")
    public UserDto addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) throws UserExistException {
        if (bindingResult.hasErrors()) {
            return null;
        }
        User user = userService.saveUser(userForm).orElse(null);
        if (user == null) {
            model.addAttribute("usernameError", "User with such name is already exist");
            return null;
        }
        return mapperUser.userToDto(user);
    }
}
