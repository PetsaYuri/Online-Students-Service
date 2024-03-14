package com.online.students.service.Users;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getOneById(Long id);

    UserEntity create(UserDTO userDTO);

    boolean delete(Long id);
}
