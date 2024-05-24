package com.online.students.service.API.Orders;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;


public record OrderDTO(Long id,
                       LocalDateTime dateOfCreation,
                       @Future LocalDateTime deadline,
                       @NotNull @NotBlank String notes,
                       String message,
                       int price,
                       Statuses status,
                       Long idCustomer,
                       Long idAssistance) {
}
