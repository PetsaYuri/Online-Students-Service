package com.online.students.service.API.Users;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Roles[] getRoles() {
        return Roles.values();
    }

    @Override
    public UserEntity getOneById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return userRepository.getReferenceById(id);
    }

    @Override
    public UserEntity getOneByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (!userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @Override
    public UserEntity create(UserDTO userDTO) {
        UserEntity existingUser = userRepository.findByEmail(userDTO.email());
        if (existingUser != null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()), "User with this email already exists");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.password());
        UserEntity newUser = new UserEntity(userDTO.fullName(), userDTO.email(), encodedPassword);
        UserEntity createdUser = userRepository.save(newUser);

        if (createdUser.getId().equals(1L)) {
            createdUser.setRole(Roles.OWNER);
            return userRepository.save(createdUser);
        }

        return createdUser;
    }

    @Override
    public UserEntity update(Long id, UserDTO userDTO) {
        UserEntity currentUser = getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        UserEntity existingUser = getOneById(id);

        if (currentUser.getRole().equals(Roles.OWNER) || currentUser.getRole().equals(Roles.ADMIN)) {
            existingUser.setFullName(userDTO.fullName());
            existingUser.setEmail(userDTO.email());
            changeRole(id, userDTO.role());
            existingUser.setBalance(userDTO.balance());
            existingUser.setImage(userDTO.image());

            if (userDTO.password() != null && !userDTO.password().isBlank()) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                existingUser.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));
            }
            return userRepository.save(existingUser);
        }

        else if (currentUser.equals(existingUser)) {
            existingUser.setFullName(userDTO.fullName());
            existingUser.setImage(userDTO.image());

            if (userDTO.password() != null && !userDTO.password().isBlank()) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                existingUser.setPassword(bCryptPasswordEncoder.encode(userDTO.password()));
            }
            return userRepository.save(existingUser);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have permission to update this user");
    }

    @Override
    public UserEntity changeAvatar(Long userId, String avatar) {
        UserEntity endUser = getOneById(userId);
        UserEntity currentUser = getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (currentUser.equals(endUser) || (currentUser.getRole().equals(Roles.ADMIN) || currentUser.getRole().equals(Roles.OWNER))) {
            endUser.setImage(avatar);
            return userRepository.save(endUser);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the avatar for this user.");
    }

    @Override
    public UserEntity changeRole(Long userId, String roleName) {
        UserEntity endUser = getOneById(userId);
        UserEntity currentUser = getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Roles role = Roles.valueOf(roleName);

        if (role.equals(Roles.OWNER)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot assign the role \"owner\" to anyone.");
        }

        if (role.equals(Roles.ADMIN) && !currentUser.getRole().equals(Roles.OWNER)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot assign the role \"admin\" if you don't have the role \"owner\".");
        }

        if (currentUser.getRole().equals(Roles.ADMIN) && (endUser.getRole().equals(Roles.ADMIN) || endUser.getRole().equals(Roles.OWNER))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the role if end user have the role \"admin\" or \"owner\".");
        }

        endUser.setRole(role);
        return userRepository.save(endUser);
    }

    @Override
    public UserEntity changeBalance(Long userId, int amount) {
        UserEntity user = getOneById(userId);
        user.setBalance(user.getBalance() + amount);

        if (user.getBalance() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your balance can't be less than 0");
        }
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        return true;
    }
}
