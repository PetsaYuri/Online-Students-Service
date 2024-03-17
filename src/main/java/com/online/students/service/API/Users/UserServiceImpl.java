package com.online.students.service.API.Users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public boolean delete(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return true;
    }
}
