package com.online.students.service.API.Assistances;

import com.online.students.service.API.AssistanceCategories.AssistanceCategoryEntity;
import com.online.students.service.API.AssistanceCategories.AssistanceCategoryService;
import com.online.students.service.API.Users.Roles;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssistanceServiceImpl implements AssistanceService{

    private final AssistanceRepository assistanceRepository;
    private final AssistanceCategoryService assistanceCategoryService;
    private final UserService userService;

    public AssistanceServiceImpl(AssistanceRepository assistanceRepository, AssistanceCategoryService assistanceCategoryService, UserService userService) {
        this.assistanceRepository = assistanceRepository;
        this.assistanceCategoryService = assistanceCategoryService;
        this.userService = userService;
    }

    @Override
    public List<AssistanceEntity> getAll(String category) {
        if (category != null) {
            AssistanceCategoryEntity assistanceCategory = assistanceCategoryService.getByTitle(category);
            return assistanceRepository.findByAssistanceCategory(assistanceCategory);

        }

        return assistanceRepository.findAll();
    }

    @Override
    public AssistanceEntity getById(Long id) {
        return assistanceRepository.getReferenceById(id);
    }

    @Override
    public AssistanceEntity create(AssistanceDTO assistanceDTO) {
        AssistanceCategoryEntity assistanceCategory = assistanceCategoryService.getById(assistanceDTO.idAssistanceCategory());
        UserEntity instructor = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!instructor.getRole().equals(Roles.INSTRUCTOR)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't create assistance, because you don't have role " + Roles.INSTRUCTOR);
        }

        AssistanceEntity assistance = new AssistanceEntity(
                assistanceDTO.title(),
                assistanceDTO.description(),
                assistanceDTO.cost(),
                assistanceCategory,
                instructor);

    return assistanceRepository.save(assistance);
    }

    @Override
    public AssistanceEntity update(Long id, AssistanceDTO assistanceDTO) {
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        AssistanceEntity existingAssistance = getById(id);

        if (currentUser.equals(existingAssistance.getInstructor()) || (currentUser.getRole().equals(Roles.OWNER) || currentUser.getRole().equals(Roles.ADMIN))) {
            existingAssistance.setTitle(assistanceDTO.title());
            existingAssistance.setDescription(assistanceDTO.description());
            existingAssistance.setCost(assistanceDTO.cost());
            AssistanceCategoryEntity assistanceCategory = assistanceCategoryService.getById(assistanceDTO.idAssistanceCategory());
            existingAssistance.setAssistanceCategory(assistanceCategory);

            return assistanceRepository.save(existingAssistance);
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have permission to update this assistance");
    }

    @Override
    public boolean delete(Long id) {
        AssistanceEntity assistance = assistanceRepository.getReferenceById(id);
        UserEntity currentUser = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (assistance.getInstructor().equals(currentUser) || currentUser.getRole().equals(Roles.ADMIN) || currentUser.getRole().equals(Roles.OWNER)) {
            assistanceRepository.delete(assistance);
            return true;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have permission to delete this assistance");
    }
}
