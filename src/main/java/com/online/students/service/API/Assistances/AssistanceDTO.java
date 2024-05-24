package com.online.students.service.API.Assistances;

import com.online.students.service.API.Orders.OrderDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record AssistanceDTO(
        Long id,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        @NotNull @Min(1) @Max(9999) int cost,
        @NotNull @Min(1) @Max(999) Long idAssistanceCategory,
        Long idInstructor,
        List<OrderDTO> orders) {
}
