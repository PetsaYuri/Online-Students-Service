package com.online.students.service.API.Users;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getOneById(Long id);

    UserEntity getOneByEmail(String email);

    UserEntity create(UserDTO userDTO);

    UserEntity update(Long id, UserDTO userDTO);

    UserEntity changeAvatar(Long userId, String avatar);

    UserEntity changeRole(Long userId, String roleName);

    boolean delete(Long id);
}
