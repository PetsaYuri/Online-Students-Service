package com.online.students.service.API.Assistances;

public record AssistanceDTO(Long id, String title, String description, int cost, Long idAssistanceCategory, Long idInstructor) {
}
