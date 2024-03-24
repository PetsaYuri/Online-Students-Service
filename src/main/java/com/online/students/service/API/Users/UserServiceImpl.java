package com.online.students.service.API.Users;

import com.online.students.service.API.ImageUploading.ImageUploadingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ImageUploadingService imageUploadingService;

    public UserServiceImpl(UserRepository userRepository, ImageUploadingService imageUploadingService) {
        this.userRepository = userRepository;
        this.imageUploadingService = imageUploadingService;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getOneById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserEntity create(UserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO.fullName(), userDTO.email(), userDTO.password());
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity uploadImage(Long userId, MultipartFile multipartFile) {
        String filename = imageUploadingService.upload(multipartFile);

        UserEntity user = userRepository.getReferenceById(userId);
        user.setImage(filename);
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return true;
    }
}
