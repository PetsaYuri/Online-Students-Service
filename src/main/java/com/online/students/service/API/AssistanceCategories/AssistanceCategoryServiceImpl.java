package com.online.students.service.API.AssistanceCategories;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceCategoryServiceImpl implements AssistanceCategoryService{

    private final AssistanceCategoryRepository assistanceCategoryRepository;

    public AssistanceCategoryServiceImpl(AssistanceCategoryRepository assistanceCategoryRepository) {
        this.assistanceCategoryRepository = assistanceCategoryRepository;
    }

    @Override
    public List<AssistanceCategoryEntity> getAll() {
        return assistanceCategoryRepository.findAll();
    }

    @Override
    public AssistanceCategoryEntity getById(Long id) {
        return assistanceCategoryRepository.getReferenceById(id);
    }

    @Override
    public AssistanceCategoryEntity create(AssistanceCategoryDTO assistanceCategoryDTO) {
        AssistanceCategoryEntity assistanceCategory = new AssistanceCategoryEntity(assistanceCategoryDTO.title(), assistanceCategoryDTO.description());
        return assistanceCategoryRepository.save(assistanceCategory);
    }

    @Override
    public AssistanceCategoryEntity update(Long id, AssistanceCategoryDTO assistanceCategoryDTO) {
        AssistanceCategoryEntity existingCategory = getById(id);

        existingCategory.setTitle(assistanceCategoryDTO.title());
        existingCategory.setDescription(assistanceCategoryDTO.description());

        return assistanceCategoryRepository.save(existingCategory);
    }

    @Override
    public boolean delete(Long id) {
        AssistanceCategoryEntity assistanceCategory = assistanceCategoryRepository.getReferenceById(id);
        assistanceCategoryRepository.delete(assistanceCategory);
        return true;
    }
}
