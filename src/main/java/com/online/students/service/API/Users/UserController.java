package com.online.students.service.API.Users;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public static final String URI_USERS_ID = "/{id}";
    public static final String URI_USERS_CHANGE_AVATAR = URI_USERS_ID + "/avatar";
    public static final String URI_USERS_CHANGE_ROLE = URI_USERS_ID + "/role";
    public static final String URI_USERS_CHANGE_BALANCE = URI_USERS_ID + "/balance";

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

    @GetMapping("/roles")
    public Roles[] getRoles() {
        return userService.getRoles();
    }

    @GetMapping(URI_USERS_ID)
    public UserDTO getOneById(@PathVariable Long id) {
        UserEntity user = userService.getOneById(id);
        return userDTOMapper.apply(user);
    }

    @PostMapping
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) {
        UserEntity user = userService.create(userDTO);
        return userDTOMapper.apply(user);
    }

    @PutMapping(URI_USERS_ID)
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserEntity user = userService.update(id, userDTO);
        return userDTOMapper.apply(user);
    }

    @PatchMapping(URI_USERS_CHANGE_AVATAR)
    public UserDTO changeAvatar(@PathVariable Long id, @RequestParam String avatar) {
        UserEntity updatedUser = userService.changeAvatar(id, avatar);
        return userDTOMapper.apply(updatedUser);
    }

    @PatchMapping(URI_USERS_CHANGE_ROLE)
    public UserDTO changeRole(@PathVariable Long id, @RequestParam String role) {
        UserEntity updatedUser = userService.changeRole(id, role);
        return userDTOMapper.apply(updatedUser);
    }

    @PatchMapping(URI_USERS_CHANGE_BALANCE)
    public UserDTO changeBalance(@PathVariable Long id, @RequestParam int amount) {
        UserEntity updatedUser = userService.changeBalance(id, amount);
        return userDTOMapper.apply(updatedUser);
    }

    @DeleteMapping(URI_USERS_ID)
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
