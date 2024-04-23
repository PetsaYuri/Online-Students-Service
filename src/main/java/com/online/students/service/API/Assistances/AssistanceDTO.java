package com.online.students.service.API.Assistances;

import com.online.students.service.API.Orders.OrderDTO;
import java.util.List;

public record AssistanceDTO(Long id, String title, String description, int cost, Long idAssistanceCategory, Long idInstructor, List<OrderDTO> orders) {
}
