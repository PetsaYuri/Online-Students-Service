package com.online.students.service.API.Auth;

import com.online.students.service.API.Users.UserDTO;
import com.online.students.service.API.Users.UserDTOMapper;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String URI_SIGNUP = "/auth/signup";
    public static final String URI_LOGIN = "/auth/login";
    public static final String URI_PROFILE = "/profile";

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    public AuthController(UserService userService, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping(URI_SIGNUP)
    public UserDTO signup(@RequestBody UserDTO userDTO) {
        UserEntity newUser = userService.create(userDTO);
        return userDTOMapper.apply(newUser);
    }

    @PostMapping(URI_LOGIN)
    public UserDTO login() {
        UserEntity user = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return userDTOMapper.apply(user);
    }

    @GetMapping(URI_PROFILE)
    public UserDTO profile() {
        UserEntity user = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return userDTOMapper.apply(user);
    }
}
