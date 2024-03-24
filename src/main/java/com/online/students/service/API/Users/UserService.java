package com.online.students.service.API.Users;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getOneById(Long id);

    UserEntity create(UserDTO userDTO);

    UserEntity uploadImage(Long userId, MultipartFile multipartFile);

    boolean delete(Long id);
}
