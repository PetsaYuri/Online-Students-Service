package com.online.students.service.Assistances;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AssistanceDTOMapper implements Function<AssistanceEntity, AssistanceDTO> {

    @Override
    public AssistanceDTO apply(AssistanceEntity assistanceEntity) {
        return new AssistanceDTO(
                assistanceEntity.getId(),
                assistanceEntity.getTitle(),
                assistanceEntity.getDescription(),
                assistanceEntity.getCost(),
                assistanceEntity.getAssistanceCategory().getId(),
                assistanceEntity.getInstructor().getId());
    }
}
