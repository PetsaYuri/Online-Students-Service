package com.online.students.service.API.Users;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    Roles[] getRoles();

    UserEntity getOneById(Long id);

    UserEntity getOneByEmail(String email);

    UserEntity create(UserDTO userDTO);

    UserEntity update(Long id, UserDTO userDTO);

    UserEntity changeAvatar(Long userId, String avatar);

    UserEntity changeRole(Long userId, String roleName);

    UserEntity changeBalance(Long userId, int amount);

    boolean delete(Long id);
}
