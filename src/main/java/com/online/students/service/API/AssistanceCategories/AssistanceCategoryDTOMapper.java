package com.online.students.service.API.AssistanceCategories;

import com.online.students.service.API.Assistances.AssistanceDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AssistanceCategoryDTOMapper implements Function<AssistanceCategoryEntity, AssistanceCategoryDTO> {

    private final AssistanceDTOMapper assistanceDTOMapper;

    public AssistanceCategoryDTOMapper(AssistanceDTOMapper assistanceDTOMapper) {
        this.assistanceDTOMapper = assistanceDTOMapper;
    }

    @Override
    public AssistanceCategoryDTO apply(AssistanceCategoryEntity assistanceCategoryEntity) {
        return new AssistanceCategoryDTO(
                assistanceCategoryEntity.getId(),
                assistanceCategoryEntity.getTitle(),
                assistanceCategoryEntity.getDescription(),
                assistanceCategoryEntity.getListOfAssistance()
                        .stream()
                        .map(assistanceDTOMapper)
                        .toList());
    }
}
