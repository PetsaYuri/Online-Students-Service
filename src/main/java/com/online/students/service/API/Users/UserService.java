package com.online.students.service.API.Users;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserEntity> getAll();

    UserEntity getOneById(Long id);

    UserEntity create(UserDTO userDTO);

    UserEntity uploadImage(Long userId, MultipartFile multipartFile) throws IOException;

    boolean delete(Long id);
}
