package com.online.students.service.API.Assistances;

import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import com.online.students.service.API.AssistanceCategories.AssistanceCategoryEntity;
import com.online.students.service.API.AssistanceCategories.AssistanceCategoryService;
import org.springframework.stereotype.Service;

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
    public List<AssistanceEntity> getAll() {
        return assistanceRepository.findAll();
    }

    @Override
    public AssistanceEntity getById(Long id) {
        return assistanceRepository.getReferenceById(id);
    }

    @Override
    public AssistanceEntity create(AssistanceDTO assistanceDTO) {
        AssistanceCategoryEntity assistanceCategory = assistanceCategoryService.getById(assistanceDTO.idAssistanceCategory());
        UserEntity instructor = userService.getOneById(assistanceDTO.idInstructor());

        AssistanceEntity assistance = new AssistanceEntity(
                assistanceDTO.title(),
                assistanceDTO.description(),
                assistanceDTO.cost(),
                assistanceCategory,
                instructor);

    return assistanceRepository.save(assistance);
    }

    @Override
    public boolean delete(Long id) {
        AssistanceEntity assistance = assistanceRepository.getReferenceById(id);
        assistanceRepository.delete(assistance);
        return true;
    }
}
