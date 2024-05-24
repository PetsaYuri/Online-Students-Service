package com.online.students.service.API.AssistanceCategories;

import com.online.students.service.API.Assistances.AssistanceDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AssistanceCategoryDTO(
        Long id,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        List<AssistanceDTO> assistanceDTOList) {
}
