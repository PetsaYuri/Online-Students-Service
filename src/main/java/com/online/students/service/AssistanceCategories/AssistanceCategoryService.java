package com.online.students.service.AssistanceCategories;

import java.util.List;

public interface AssistanceCategoryService {

    List<AssistanceCategoryEntity> getAll();

    AssistanceCategoryEntity getById(Long id);

    AssistanceCategoryEntity create(AssistanceCategoryDTO assistanceCategoryDTO);

    boolean delete(Long id);
}
