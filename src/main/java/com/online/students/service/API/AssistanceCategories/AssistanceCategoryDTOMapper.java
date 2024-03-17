package com.online.students.service.API.AssistanceCategories;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AssistanceCategoryDTOMapper implements Function<AssistanceCategoryEntity, AssistanceCategoryDTO> {

    @Override
    public AssistanceCategoryDTO apply(AssistanceCategoryEntity assistanceCategoryEntity) {
        return new AssistanceCategoryDTO(
                assistanceCategoryEntity.getId(),
                assistanceCategoryEntity.getTitle(),
                assistanceCategoryEntity.getDescription());
    }
}
