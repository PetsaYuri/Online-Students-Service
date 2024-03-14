package com.online.students.service.Users;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final String USER_ID_URI = "/{id}";

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    public UserController(UserService userService, UserDTOMapper userDTOMapper) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll().stream().map(userDTOMapper).collect(Collectors.toList());
    }

    @GetMapping(USER_ID_URI)
    public UserDTO getOneById(@PathVariable Long id) {
        UserEntity user = userService.getOneById(id);
        return userDTOMapper.apply(user);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.create(userDTO);
        return userDTOMapper.apply(user);
    }

    @DeleteMapping(USER_ID_URI)
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
