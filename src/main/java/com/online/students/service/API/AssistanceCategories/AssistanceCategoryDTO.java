package com.online.students.service.API.AssistanceCategories;

import com.online.students.service.API.Assistances.AssistanceDTO;

import java.util.List;

public record AssistanceCategoryDTO(Long id, String title, String description, List<AssistanceDTO> assistanceDTOList) {
}
